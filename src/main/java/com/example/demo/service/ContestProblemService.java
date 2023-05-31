package com.example.demo.service;

import com.example.demo.dto.contestProblem.GetContestProblemByContestDto;
import com.example.demo.dto.contestProblem.SaveContestProblemDto;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContestProblemService {

    private final ContestProblemRepository contestProblemRepository;
    private final ContestRepository contestRepository;
    private final ProblemRepository problemRepository;


    public SaveContestProblemResponseDto saveContestProblem(SaveContestProblemDto registerProblemDto) {
        Contest contest = contestRepository.findById(registerProblemDto.getContestId()).orElse(null);
        Problem problem = problemRepository.findById(registerProblemDto.getProblemId()).orElse(null);
        ContestProblem contestProblem = ContestProblem.toEntity(registerProblemDto, problem, contest);
        SaveContestProblemResponseDto responseDto = SaveContestProblemResponseDto.from(contestProblemRepository.save(contestProblem));
        return responseDto;
    }

    public List<GetContestProblemByContestDto> getContestProblemList(Long contestId) {
        return contestProblemRepository.findContestProblemListContest(contestId);
    }

    public void updateContestProblem(SaveContestProblemDto requestDto, Long contestProblemId) {
        Optional<ContestProblem> contestProblemOptional = contestProblemRepository.findById(contestProblemId);
        if (contestProblemOptional.isEmpty()) {
            System.out.println("Contest Problem이 없습니다. ");
            return;
        }
        ContestProblem contestProblem = contestProblemOptional.get();
        Optional<Problem> problemOptional = problemRepository.findById(requestDto.getProblemId());
        if (problemOptional.isEmpty()) {
            System.out.println("Problem이 없습니다. ");
            return;
        }
        Problem problem = problemOptional.get();
        contestProblem.update(requestDto.getTitle(), requestDto.getSequence(), problem);
        contestProblemRepository.save(contestProblem);
    }

    public void deleteContestProblem(Long contestProblemId) {
        contestProblemRepository.deleteById(contestProblemId);
    }
}
