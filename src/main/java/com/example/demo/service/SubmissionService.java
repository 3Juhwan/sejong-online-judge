package com.example.demo.service;

import com.example.demo.dto.submission.CreateSubmissionDto;
import com.example.demo.dto.submission.GetSubmissionDto;
import com.example.demo.entity.ContestProblem;
import com.example.demo.entity.Submission;
import com.example.demo.entity.User;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final ContestProblemRepository contestProblemRepository;
    private final SubmitStatusRepository submitStatusRepository;
    private final ProblemRepository problemRepository;


    @Transactional
    public void saveSubmission(CreateSubmissionDto submissionDto) {

        User user = userRepository.findByUsername(submissionDto.getUsername()).get();
        ContestProblem contestProblem = contestProblemRepository.findById(submissionDto.getContestProblemId()).get();

        Submission submission = CreateSubmissionDto.toEntity(submissionDto, user, contestProblem, contestProblem.getProblem());
        submissionRepository.save(submission);
    }

//    public List<CreateSubmissionDto> getSubmissionByUsername(String username) {
//        User user = userRepository.findByUsername(username).get();
//        List<Submission> submissions = submissionRepository.findByUser(user).get();
//
//        return submissions.stream()
//                .map(submission -> CreateSubmissionDto.from(submission))
//                .collect(Collectors.toList());
//    }

//    public List<GetSubmissionDto> getSubmissions(String username, Long contestProblemId) {
//        User user = userRepository.findByUsername(username).orElse(null);
//        ContestProblem contestProblem = contestProblemRepository.findById(contestProblemId).orElse(null);
//        return submissionRepository.findAllByUserAndContestProblem(user, contestProblem)
//                .stream()
//                .map(s -> GetSubmissionDto.)
//    }

//    public SubmissionDto getSubmissionByUserAndProblem(User user, Problem problem) {
//
//    }

    /**
     * 사용자가 문제에 최초로 제출하는 가?
     * @param registeredProblem
     * @param user
     * @return boolean
     */
//    private boolean isFirstSubmit(ContestProblem registeredProblem, User user) {
//        return submitStatusRepository.findByRegisteredProblemAndAndUser(registeredProblem, user).isEmpty();
//    }

//    private void createSubmitStatus(SubmissionDto submissionDto, RegisteredProblem registeredProblem, User user) {
//        SubmitStatus.builder()
//                .
//    }

}
