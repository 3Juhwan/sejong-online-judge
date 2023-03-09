package com.example.demo.dto.problem;

import com.example.demo.entity.Problem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProblemDto {

    private String title;
    private String content;
    private Long timeLimit;
    private Long memoryLimit;


    public static CreateProblemDto from(@NotNull Problem problem) {
        return CreateProblemDto.builder()
                .title(problem.getTitle())
                .content(problem.getContent())
                .timeLimit(problem.getTimeLimit())
                .memoryLimit(problem.getMemoryLimit())
                .build();
    }

    public static Problem toEntity(@NotNull CreateProblemDto problemDto) {
        return Problem.builder()
                .title(problemDto.getTitle())
                .content(problemDto.getContent())
                .timeLimit(problemDto.getTimeLimit())
                .memoryLimit(problemDto.getMemoryLimit())
                .build();
    }
}