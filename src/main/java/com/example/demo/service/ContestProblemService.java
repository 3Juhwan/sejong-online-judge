package com.example.demo.service;

import com.example.demo.dto.contestProblem.SaveContestProblemToContestDto;
import com.example.demo.entity.Contest;
import com.example.demo.entity.ContestProblem;
import com.example.demo.entity.Problem;
import com.example.demo.repository.ContestProblemRepository;
import com.example.demo.repository.ContestRepository;
import com.example.demo.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContestProblemService {

    private final ContestProblemRepository registeredProblemRepository;
    private final ContestRepository contestRepository;
    private final ProblemRepository problemRepository;


    public void saveContestProblemToContest(SaveContestProblemToContestDto registerProblemDto) {
        Contest contest = contestRepository.findById(registerProblemDto.getContestId()).get();
        List<Problem> problems = registerProblemDto.getProblemList()
                .stream()
                .map(p -> problemRepository.findById(p.getProblemId()).get())
                .collect(Collectors.toList());
        for (Problem problem : problems) {
            ContestProblem registeredProblem = ContestProblem.toEntity(problem, contest);
            registeredProblemRepository.save(registeredProblem);
        }
    }
}
