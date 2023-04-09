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
public class UpdateProblemDto {

    private Long problemId;
    private String title;
    private String content;
    private Long timeLimit;
    private Long memoryLimit;
    private String dirPath;

    public static UpdateProblemDto from(@NotNull Problem problem) {
        return UpdateProblemDto.builder()
                .problemId(problem.getId())
                .title(problem.getTitle())
                .content(problem.getContent())
                .timeLimit(problem.getTimeLimit())
                .memoryLimit(problem.getMemoryLimit())
                .dirPath(problem.getDirPath())
                .build();
    }

    public static Problem toEntity(UpdateProblemDto problemDto) {
        return Problem.builder()
                .id(problemDto.problemId)
                .title(problemDto.title)
                .content(problemDto.content)
                .timeLimit(problemDto.timeLimit)
                .memoryLimit(problemDto.memoryLimit)
                .dirPath(problemDto.dirPath)
                .build();
    }
}
