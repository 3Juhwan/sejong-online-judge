package com.example.demo.dto.problem;

import com.example.demo.entity.Problem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProblemDto {

    private Long problemId;
    private String title;
    private String content;
    private Long timeLimit;
    private Long memoryLimit;

    public static Problem toEntity(UpdateProblemDto problemDto) {
        return Problem.builder()
                .id(problemDto.problemId)
                .title(problemDto.title)
                .content(problemDto.content)
                .timeLimit(problemDto.timeLimit)
                .memoryLimit(problemDto.memoryLimit)
                .build();
    }
}
