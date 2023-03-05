package com.example.demo.service;

import com.example.demo.dto.submission.SubmissionDto;
import com.example.demo.entity.Problem;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.Language;
import com.example.demo.entity.Submission;
import com.example.demo.repository.ProblemRepository;
import com.example.demo.repository.SubmissionRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;

    @Transactional
    public SubmissionDto saveSubmission(SubmissionDto submissionDto) {
        User user = userRepository.findByUsername(submissionDto.getUsername()).get();
        Problem problem = problemRepository.findById(submissionDto.getProblemId()).get();
        Language language = Language.find(submissionDto.getLanguage());

        Submission submission = Submission.builder()
                .user(user)
                .problem(problem)
                .code(submissionDto.getCode())
                .language(language)
                .length((long) submissionDto.getCode().length())
                .build();

        return SubmissionDto.from(submissionRepository.save(submission));
    }

    public List<SubmissionDto> getSubmissionByUsername(String username) {
        User user = userRepository.findByUsername(username).get();
        List<Submission> submissions = submissionRepository.findByUser(user).get();

        return submissions.stream()
                .map(submission -> SubmissionDto.from(submission))
                .collect(Collectors.toList());
    }

//    public SubmissionDto getSubmissionByUserAndProblem(User user, Problem problem) {
//
//    }


}
