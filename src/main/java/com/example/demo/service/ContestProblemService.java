package com.example.demo.service;

import com.example.demo.dto.contestProblem.GetContestProblemByContestDto;
import com.example.demo.dto.contestProblem.SaveContestProblemToContestDto;
import com.example.demo.entity.Contest;
import com.example.demo.entity.ContestProblem;
import com.example.demo.entity.Problem;
import com.example.demo.repository.ContestProblemRepository;
import com.example.demo.repository.ContestRepository;
import com.example.demo.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContestProblemService {

    private final ContestProblemRepository contestProblemRepository;
    private final ContestRepository contestRepository;
    private final ProblemRepository problemRepository;


    public void saveContestProblemToContest(Long contestId, SaveContestProblemToContestDto registerProblemDto) {
        Contest contest = contestRepository.findById(contestId).orElse(null);
        List<Problem> problems = registerProblemDto.getProblemList()
                .stream()
                .map(p -> problemRepository.findById(p.getProblemId()).orElse(null))
                .toList();
        int idx = 0;
        for (Problem problem : problems) {
            ContestProblem contestProblem = ContestProblem.toEntity(registerProblemDto.getProblemList().get(idx++), problem, contest);
            contestProblemRepository.save(contestProblem);
        }
    }

    public List<GetContestProblemByContestDto> getContestProblems(Long contestId, Principal principal) {
        List<GetContestProblemByContestDto> contestProblems = contestProblemRepository.findAllContestProblemByContest(contestId);
        return contestProblems;
    }
}
