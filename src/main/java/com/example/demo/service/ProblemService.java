package com.example.demo.service;

import com.example.demo.dto.problem.CreateProblemDto;
import com.example.demo.dto.problem.GetProblemInfoDto;
import com.example.demo.dto.problem.UpdateProblemDto;
import com.example.demo.entity.Problem;
import com.example.demo.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;

    public CreateProblemDto saveProblem(CreateProblemDto problemDto) {
        Problem problem = CreateProblemDto.toEntity(problemDto);
        return CreateProblemDto.from(problemRepository.save(problem));
    }

    public UpdateProblemDto updateProblem(UpdateProblemDto problemDto) {
        Problem updatedProblem = problemRepository
                .findById(problemDto.getProblemId()).orElse(null)
                .updateEntity(problemDto);
        return UpdateProblemDto.from(problemRepository.save(updatedProblem));
    }

    public void deleteProblem(Long problemId) {
        problemRepository.deleteById(problemId);
    }

    public GetProblemInfoDto getOneProblem(Long problemId) {
        return GetProblemInfoDto.from(problemRepository.findById(problemId).orElse(null));
    }

}
