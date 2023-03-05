package com.example.demo.service;

import com.example.demo.dto.problem.ProblemDto;
import com.example.demo.entity.Problem;
import com.example.demo.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemDto saveProblem(ProblemDto problemDto) {
        Problem problem = ProblemDto.toEntity(problemDto);
        return ProblemDto.from(problemRepository.save(problem));
    }

    public ProblemDto updateProblem(ProblemDto problemDto) {
        Problem updatedProblem = problemRepository
                                    .findById(problemDto.getId()).get()
                                    .updateEntity(problemDto);
        return ProblemDto.from(problemRepository.save(updatedProblem));
    }

    public void deleteProblem(Long problemId) {
        problemRepository.deleteById(problemId);
    }

}
