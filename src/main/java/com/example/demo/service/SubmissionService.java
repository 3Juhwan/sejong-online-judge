package com.example.demo.service;

import com.example.demo.dto.grade.GetGradedScoreBoardResponseDto;
import com.example.demo.dto.submission.*;
import com.example.demo.dto.submission.CreateSampleSubmissionDto.SampleCase;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final ContestProblemRepository contestProblemRepository;
    private final ContestRepository contestRepository;
    private final GradeCodeService gradeCodeService;
    private final ProblemRepository problemRepository;
    private final TestDataRepository testDataRepository;
    private final SubmitStatusService submitStatusService;
    private final CourseUserRepository courseUserRepository;
    private final GradeRepository gradeRepository;


    public List<SubmissionResponseDto> getSampleSubmission(CreateSampleSubmissionDto submissionDto) {
        return gradeCodeService.gradeSubmission(submissionDto.getSourceCode(), submissionDto.getSampleCaseList());
    }

    @Transactional
    public List<SubmissionResponseDto> getHiddenSubmission(CreateSampleSubmissionDto submissionDto, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElse(null);
        ContestProblem contestProblem = contestProblemRepository.findById(submissionDto.getContestProblemId()).orElse(null);
        Problem problem = problemRepository.findById(contestProblem.getProblem().getId()).orElse(null);
        List<SampleCase> sampleCases = testDataRepository.findAllTestDataIdByProblem(problem).orElse(null).stream()
                .map(x -> SampleCase.builder().dataSequence(x.getDataSequence()).inputData(x.getInputData()).outputData(x.getOutputData()).build()).toList();
        List<SubmissionResponseDto> submissionResponseDtoList = gradeCodeService.gradeSubmission(submissionDto.getSourceCode(), sampleCases);
        Submission submission = saveSubmission(submissionDto, submissionResponseDtoList, user);
        submitStatusService.updateSubmitStatus(submission, principal, submissionDto.getContestProblemId());
        return submissionResponseDtoList;
    }

    public Submission saveSubmission(CreateSampleSubmissionDto submissionDto, List<SubmissionResponseDto> submissionResponseDtoList, User user) {
        ContestProblem contestProblem = contestProblemRepository.findById(submissionDto.getContestProblemId()).orElse(null);

        Long timeUsage = 0L;
        Long memoryUsage = 0L;
        Long status = 0L;
        Long correctCnt = 0L;
        Long score = 0L;
        List<String> resultList = new ArrayList<>();

        for (SubmissionResponseDto res : submissionResponseDtoList) {
            if (res.getUsedTime() != null) {
                timeUsage = Math.max(timeUsage, res.getUsedTime());
            }
            if (res.getUsedMem() != null) {
                memoryUsage = Math.max(memoryUsage, res.getUsedMem());
            }
            resultList.add(res.getResult());
            status = Math.max(status, res.getStatus());
            if (res.getStatus() == 0) {
                correctCnt++;
            }

        }

        if (submissionResponseDtoList.size() != 0) {
            score = correctCnt * 100 / submissionResponseDtoList.size();
        }

        Submission submission = Submission.builder()
                .code(submissionDto.getSourceCode())
                .result(String.join("\n\n--------------------\n\n", resultList))
                .length((long) submissionDto.getSourceCode().length())
                .problem(contestProblem.getProblem())
                .contestProblem(contestProblem)
                .user(user)
                .timeUsage(timeUsage)
                .memoryUsage(memoryUsage)
                .status(status)
                .score(score)
                .build();

        return submissionRepository.save(submission);
    }

    public Page<GetSubmissionDto> getSubmissionListByContestProblem(Principal principal, Long contestProblemId, String username, String status, Pageable pageable) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null && !username.isEmpty()) {
            return getEmptyPage();
        }
        ContestProblem contestProblem = contestProblemRepository.findById(contestProblemId).orElse(null);
        return new PageImpl<>(submissionRepository.findAllByConditions(user, contestProblem, status, pageable).orElse(null).stream()
                .map(submission -> GetSubmissionDto.from(submission, principal, false))
                .collect(Collectors.toList()));
    }

    public Page<GetSubmissionDto> getSubmissionListByContest(Principal principal, Long contestId, String username, String status, Pageable pageable) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null && !username.isEmpty()) {
            return getEmptyPage();
        }
        Contest contest = contestRepository.findById(contestId).orElse(null);
        if (contest == null) {
            return getEmptyPage();
        }
        List<ContestProblem> contestProblemList = contestProblemRepository.findAllContestProblemByContestId(contestId).orElse(null);
        if (contestProblemList == null) {
            return getEmptyPage();
        }
        return new PageImpl<>(
                contestProblemList.stream()
                        .flatMap(contestProblem ->
                                submissionRepository.findAllByConditions(user, contestProblem, status, pageable).orElse(null).stream()
                                        .map(submission -> GetSubmissionDto.from(submission, principal, false))
                        )
                        .collect(Collectors.toList())
        );

    }

    public GetSubmissionDto getSubmissionWithSourceCode(Principal principal, Long submissionId) {
        return GetSubmissionDto.from(submissionRepository.findById(submissionId).orElse(null), principal, true);
    }

    public List<GetTotalSubmissionDto> getTotalStatus(Long contestId, LocalDateTime endingTime) {

        // contest 갖고 오고
        Contest contest = contestRepository.findById(contestId).orElse(null);

        // course 갖고 오고
        Course course = contest.getCourse();

        // total user 갖고 오고 (n) 권한 걸러내고
        List<User> userList = courseUserRepository.findAllByCourse(course).orElse(null).stream()
                .map(CourseUser::getUser)
                .filter(user -> user.getAuthority().equals("ROLE_STUDENT"))
                .toList();

        List<ContestProblem> contestProblemList = contestProblemRepository.findAllByContest(contest).orElse(null);

        // 위 정보로 submit-status 다 갖고 오기 (n*m)
        return userList.stream()
                .map(user -> {
                            GetTotalSubmissionDto submissionDto = GetTotalSubmissionDto.builder().username(user.getUsername()).submissionList(new ArrayList<>()).build();
                            contestProblemList
                                    .forEach(contestProblem -> submissionDto.addItem(submissionRepository.findBestSubmissionOnTime(user, contestProblem, endingTime).orElse(null)));
                            return submissionDto;
                        }
                ).toList();
    }

    // 빈 페이지를 반환하는 예제 메서드
    public Page<GetSubmissionDto> getEmptyPage() {
        List<GetSubmissionDto> emptyList = Collections.emptyList();
        PageRequest pageRequest = PageRequest.of(0, 10);
        return new PageImpl<>(emptyList, pageRequest, 0);
    }

    public List<GetGradedScoreBoardResponseDto> getGradedScoreBoard(Long gradeId) {

        Optional<Grade> gradeOptional = gradeRepository.findById(gradeId);
        if(gradeOptional.isEmpty()) {
            System.out.println("grade가 없습니다. ");
            return null;
        }
        Grade grade = gradeOptional.get();

        // contest 갖고 오고
        Contest contest = contestRepository.findById(grade.getContest().getId()).orElse(null);

        // course 갖고 오고
        Course course = contest.getCourse();

        // total user 갖고 오고 (n) 권한 걸러내고
        List<User> userList = courseUserRepository.findAllByCourse(course).orElse(null).stream()
                .map(CourseUser::getUser)
                .filter(user -> user.getAuthority().equals("ROLE_STUDENT"))
                .toList();

        List<ContestProblem> contestProblemList = contestProblemRepository.findAllByContest(contest).orElse(null);

        // 위 정보로 submit-status 다 갖고 오기 (n*m)
        return userList.stream()
                .map(user -> {
                            GetGradedScoreBoardResponseDto submissionDto = GetGradedScoreBoardResponseDto.builder().username(user.getUsername()).submissionList(new ArrayList<>()).build();
                            contestProblemList
                                    .forEach(contestProblem -> submissionDto.addItem(submissionRepository.findBestSubmissionOnTime(user, contestProblem, grade.getReferenceTime()).orElse(null)));
                            return submissionDto;
                        }
                ).toList();
    }

    @Transactional
    public void saveGradedScore(List<PostGradedScoreRequestDto> requestDtoList) {
        for (PostGradedScoreRequestDto requestDto : requestDtoList) {
            Optional<Submission> submissionOptional = submissionRepository.findById(requestDto.getSubmissionId());
            if (submissionOptional.isEmpty()) {
                System.out.println("Submission이 없습니다. ");
                continue;
            }
            Submission submission = submissionOptional.get();
            System.out.println("requestDto.getComment() = " + requestDto.getComment());
            submission.updateGradedScore(requestDto.getGradedScore(), requestDto.getComment());
            submissionRepository.save(submission);
        }
    }

}