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
public class GetProblemDto {

    private Long problemId;
    private String title;
    private String content;
    private Long timeLimit;
    private Long memoryLimit;

    public static GetProblemDto from(Problem problem) {
        return GetProblemDto.builder()
                .problemId(problem.getId())
                .title(problem.getTitle())
                .content(problem.getContent())
                .timeLimit(problem.getTimeLimit())
                .memoryLimit(problem.getMemoryLimit())
                .build();
    }
}
