package com.example.demo.service;

import com.example.demo.dto.contestProblem.GetContestProblemDto;
import com.example.demo.dto.contestProblem.SaveContestProblemToContestDto;
import com.example.demo.dto.submitstatus.GetSubmitStatusDto;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContestProblemService {

    private final ContestProblemRepository contestProblemRepository;
    private final ContestRepository contestRepository;
    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;
    private final SubmitStatusRepository submitStatusRepository;


    public void saveContestProblemToContest(Long contestId, SaveContestProblemToContestDto registerProblemDto) {
        Contest contest = contestRepository.findById(contestId).orElse(null);
        List<Problem> problems = registerProblemDto.getProblemList()
                .stream()
                .map(p -> problemRepository.findById(p.getProblemId()).orElse(null))
                .collect(Collectors.toList());
        int idx = 0;
        for (Problem problem : problems) {
            ContestProblem contestProblem = ContestProblem.toEntity(registerProblemDto.getProblemList().get(idx++), problem, contest);
            contestProblemRepository.save(contestProblem);
        }
    }

    public List<GetContestProblemDto> getContestProblems(Long contestId, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElse(null);
        List<ContestProblem> contestProblems = contestProblemRepository.findAllByContest_Id(contestId);
        return contestProblems.stream()
                .map(cp -> {
                    SubmitStatus submitStatus = submitStatusRepository.findByUser(user).orElse(null);
                    if (submitStatus == null) {
                        submitStatus = submitStatusRepository.save(SubmitStatus.getInitEntity(user));
                    }
                    return GetContestProblemDto.from(cp, GetSubmitStatusDto.from(submitStatus));
                })
                .collect(Collectors.toList());
    }
}
