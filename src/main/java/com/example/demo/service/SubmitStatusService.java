package com.example.demo.service;

import com.example.demo.entity.ContestProblem;
import com.example.demo.entity.Submission;
import com.example.demo.entity.SubmitStatus;
import com.example.demo.entity.User;
import com.example.demo.repository.ContestProblemRepository;
import com.example.demo.repository.SubmitStatusRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class SubmitStatusService {

    private final SubmitStatusRepository submitStatusRepository;
    private final UserRepository userRepository;
    private final ContestProblemRepository contestProblemRepository;


    public void updateSubmitStatus(Submission submission, Principal principal, Long contestProblemId) {
        if (submission == null) {
            return;
        }
        User user = userRepository.findByUsername(principal.getName()).orElse(null);
        ContestProblem contestProblem = contestProblemRepository.findById(contestProblemId).orElse(null);
        SubmitStatus submitStatus = submitStatusRepository.findByUserAndContestProblem(user, contestProblem).orElse(null);
        SubmitStatus updatedSubmitStatus = submitStatus.updateEntity(submission.getScore(), submission);
        submitStatusRepository.save(updatedSubmitStatus);
    }

}
