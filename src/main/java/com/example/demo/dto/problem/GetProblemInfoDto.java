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
public class GetProblemInfoDto {

    private Long problemId;
    private String title;
    private String content;
    private Long timeLimit;
    private Long memoryLimit;
    private String dirPath;

    public static GetProblemInfoDto from(Problem problem) {
        return GetProblemInfoDto.builder()
                .problemId(problem.getId())
                .title(problem.getTitle())
                .content(problem.getContent())
                .timeLimit(problem.getTimeLimit())
                .memoryLimit(problem.getMemoryLimit())
                .dirPath(problem.getDirPath())
                .build();
    }
}
