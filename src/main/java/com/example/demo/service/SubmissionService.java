package com.example.demo.service;

import com.example.demo.dto.SubmissionDto;
import com.example.demo.entity.Problem;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.Language;
import com.example.demo.entity.submission.Submission;
import com.example.demo.repository.ProblemRepository;
import com.example.demo.repository.SubmissionRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;

    public SubmissionDto saveSubmission(SubmissionDto submissionDto) {

        User user = userRepository.findByUsername(submissionDto.getUsername()).get();
        Problem problem = problemRepository.findById(submissionDto.getProblemId()).get();
        Language language = Language.find(submissionDto.getLanguage());

        if(user == null) {
            return null;
        }

        Submission submission = Submission.builder()
                .user(user)
                .problem(problem)
                .code(submissionDto.getCode())
                .language(language)
                .length((long) submissionDto.getCode().length())
                .build();

        return SubmissionDto.from(submissionRepository.save(submission));
    }

//    @Transactional(readOnly = true)
//    public SubmissionDto getSubmissionByUserAndProblem(User user, Problem problem) {
//
//    }


}
