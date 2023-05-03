package com.example.demo.service;

import com.example.demo.dto.submission.SubmissionResponseDto;
import com.example.demo.dto.submitstatus.CreateSubmitStatusDto;
import com.example.demo.entity.ContestProblem;
import com.example.demo.entity.SubmitStatus;
import com.example.demo.entity.User;
import com.example.demo.repository.ContestProblemRepository;
import com.example.demo.repository.SubmitStatusRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmitStatusService {

    private final SubmitStatusRepository submitStatusRepository;
    private final UserRepository userRepository;
    private final ContestProblemRepository contestProblemRepository;

    public void saveSubmitStatus(CreateSubmitStatusDto submitStatusDto) {

    }

    public void updateSubmitStatus(List<SubmissionResponseDto> submissionResponseDtoList, Principal principal, Long contestProblemId) {
        if (submissionResponseDtoList == null) {
            return;
        }
        User user = userRepository.findByUsername(principal.getName()).orElse(null);
        ContestProblem contestProblem = contestProblemRepository.findById(contestProblemId).orElse(null);
        SubmitStatus submitStatus = submitStatusRepository.findByUserAndContestProblem(user, contestProblem).get();
        Long count = submissionResponseDtoList.stream().filter(submission -> submission.getResult().contains(submission.getOutputData())).count();
        if (submissionResponseDtoList.size() != 0) {
            SubmitStatus updatedSubmitStatus = submitStatus.updateEntity(count / submissionResponseDtoList.size() * 100);
            submitStatusRepository.save(updatedSubmitStatus);
        }
    }

}
