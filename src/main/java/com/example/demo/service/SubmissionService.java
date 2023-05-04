package com.example.demo.service;

import com.example.demo.dto.submission.CreateSampleSubmissionDto;
import com.example.demo.dto.submission.CreateSampleSubmissionDto.SampleCase;
import com.example.demo.dto.submission.GetSubmissionDto;
import com.example.demo.dto.submission.SubmissionResponseDto;
import com.example.demo.entity.ContestProblem;
import com.example.demo.entity.Problem;
import com.example.demo.entity.Submission;
import com.example.demo.entity.User;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final ContestProblemRepository contestProblemRepository;
    private final GradeCodeService gradeCodeService;
    private final ProblemRepository problemRepository;
    private final TestDataRepository testDataRepository;
    private final SubmitStatusService submitStatusService;


    public List<SubmissionResponseDto> getSampleSubmission(CreateSampleSubmissionDto submissionDto) {
        return gradeCodeService.gradeSubmission(submissionDto.getSourceCode(), submissionDto.getSampleCaseList());
    }

    @Transactional
    public List<SubmissionResponseDto> getHiddenSubmission(CreateSampleSubmissionDto submissionDto, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElse(null);
        ContestProblem contestProblem = contestProblemRepository.findById(submissionDto.getContestProblemId()).orElse(null);
        Problem problem = problemRepository.findById(contestProblem.getId()).orElse(null);
        List<SampleCase> sampleCases = testDataRepository.findAllTestDataIdByProblem(problem).orElse(null).stream()
                .map(x -> SampleCase.builder().dataSequence(x.getDataSequence()).inputData(x.getInputData()).outputData(x.getOutputData()).build()).toList();
        List<SubmissionResponseDto> submissionResponseDtoList = gradeCodeService.gradeSubmission(submissionDto.getSourceCode(), sampleCases);
        saveSubmission(submissionDto, submissionResponseDtoList, user);
        submitStatusService.updateSubmitStatus(submissionResponseDtoList, principal, submissionDto.getContestProblemId());
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
            score = correctCnt / submissionResponseDtoList.size() * 100;
        }

        Submission submission = Submission.builder()
                .code(submissionDto.getSourceCode())
                .result(String.join("\n\n--------------------\n\n", resultList))
                .length(Long.valueOf(submissionDto.getSourceCode().length()))
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

    public Page<GetSubmissionDto> getSubmissionByCondition(Principal principal, Long contestProblemId, String username, String status, Pageable pageable) {
        User user = userRepository.findByUsername(username).orElse(null);
        ContestProblem contestProblem = contestProblemRepository.findById(contestProblemId).orElse(null);
        return new PageImpl<>(submissionRepository.findAllByConditions(user, contestProblem, status, pageable).orElse(null)
                .stream()
                .map(submission -> GetSubmissionDto.from(submission, principal, false))
                .collect(Collectors.toList()));
    }

    public GetSubmissionDto getSubmissionWithSourceCode(Principal principal, Long submissionId) {
        return GetSubmissionDto.from(submissionRepository.findById(submissionId).orElse(null), principal, true);
    }

}
