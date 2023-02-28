package com.example.demo.service;

import com.example.demo.dto.ProblemDto;
import com.example.demo.entity.Problem;
import com.example.demo.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemDto saveProblem(ProblemDto problemDto) {

        Problem problem = Problem.builder()
                .title(problemDto.getTitle())
                .content(problemDto.getContent())
                .timeLimit(problemDto.getTimeLimit())
                .memoryLimit(problemDto.getMemoryLimit())
                .build();

        return ProblemDto.from(problemRepository.save(problem));
    }
}
