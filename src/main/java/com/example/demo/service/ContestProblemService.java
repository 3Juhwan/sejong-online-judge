package com.example.demo.service;

import com.example.demo.dto.contestProblem.GetContestProblemByContestDto;
import com.example.demo.dto.contestProblem.SaveContestProblemDto;
import com.example.demo.dto.contestProblem.SaveContestProblemDto.ProblemInfo;
import com.example.demo.dto.contestProblem.SaveContestProblemResponseDto;
import com.example.demo.entity.Contest;
import com.example.demo.entity.ContestProblem;
import com.example.demo.entity.Problem;
import com.example.demo.repository.ContestProblemRepository;
import com.example.demo.repository.ContestRepository;
import com.example.demo.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContestProblemService {

    private final ContestProblemRepository contestProblemRepository;
    private final ContestRepository contestRepository;
    private final ProblemRepository problemRepository;


    public SaveContestProblemResponseDto saveContestProblem(SaveContestProblemDto registerProblemDto) {
        Contest contest = contestRepository.findById(registerProblemDto.getContestId()).orElse(null);
        SaveContestProblemResponseDto responseDto = new SaveContestProblemResponseDto(contest.getId());
        for (ProblemInfo problemInfo : registerProblemDto.getProblemList()) {
            Problem problem = problemRepository.findById(problemInfo.getProblemId()).orElse(null);
            ContestProblem contestProblem = ContestProblem.toEntity(problemInfo, problem, contest);
            responseDto.addProblemInfo(contestProblemRepository.save(contestProblem));
        }
        return responseDto;
    }

    public List<GetContestProblemByContestDto> getContestProblemList(Long contestId) {
        return contestProblemRepository.findContestProblemListContest(contestId);
    }
}
