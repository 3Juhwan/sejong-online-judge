package com.example.demo.service;

import com.example.demo.dto.submission.CreateSubmissionDto;
import com.example.demo.dto.submission.GetSubmissionDto;
import com.example.demo.entity.ContestProblem;
import com.example.demo.entity.Submission;
import com.example.demo.entity.User;
import com.example.demo.repository.ContestProblemRepository;
import com.example.demo.repository.SubmissionRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private final ContestProblemRepository contestProblemRepository;
    private final GradeCodeService gradeCodeService;


    @Transactional
    public void saveSubmission(CreateSubmissionDto submissionDto) {
        User user = userRepository.findByUsername(submissionDto.getUsername()).orElse(null);
        ContestProblem contestProblem = contestProblemRepository.findById(submissionDto.getContestProblemId()).orElse(null);
        Submission submission = CreateSubmissionDto.toEntity(submissionDto, user, contestProblem, contestProblem.getProblem());
        Submission savedSubmission = submissionRepository.save(submission);
        ResponseEntity<Object> objectResponseEntity = gradeCodeService.gradeSubmission(savedSubmission.getId());
    }

    public List<CreateSubmissionDto> getSubmissionByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        List<Submission> submissions = submissionRepository.findByUser(user).orElse(null);

        return submissions.stream()
                .map(CreateSubmissionDto::from)
                .collect(Collectors.toList());
    }

    public List<GetSubmissionDto> getSubmissionByUserAndProblem(String username, Long contestProblemId) {
        User user = userRepository.findByUsername(username).orElse(null);
        ContestProblem contestProblem = contestProblemRepository.findById(contestProblemId).orElse(null);
        return submissionRepository.findAllByUserAndContestProblem(user, contestProblem).orElse(null)
                .stream()
                .map(GetSubmissionDto::from)
                .collect(Collectors.toList());
    }

}
