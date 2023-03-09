package com.example.demo.service;

import com.example.demo.dto.problem.GetProblemDto;
import com.example.demo.dto.problem.CreateProblemDto;
import com.example.demo.dto.problem.UpdateProblemDto;
import com.example.demo.entity.Problem;
import com.example.demo.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;

    public void saveProblem(CreateProblemDto problemDto) {
        Problem problem = CreateProblemDto.toEntity(problemDto);
        problemRepository.save(problem);
    }

    public CreateProblemDto updateProblem(UpdateProblemDto problemDto) {
        Problem updatedProblem = problemRepository
                .findById(problemDto.getProblemId()).get()
                .updateEntity(problemDto);
        return CreateProblemDto.from(problemRepository.save(updatedProblem));
    }

    public void deleteProblem(Long problemId) {
        problemRepository.deleteById(problemId);
    }

    public GetProblemDto getOneProblem(Long problemId) {
        return GetProblemDto.from(problemRepository.findById(problemId).get());
    }

}
