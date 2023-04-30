package com.example.demo.dto.problem;

import com.example.demo.entity.Problem;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetProblemInfoDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long problemId;
    private String title;
    private String content;
    private Long timeLimit;
    private Long memoryLimit;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String dirPath;

    public static GetProblemInfoDto from(Problem problem, Boolean isHidden) {
        if (isHidden) {
            return GetProblemInfoDto.builder()
                    .title(problem.getTitle())
                    .content(problem.getContent())
                    .timeLimit(problem.getTimeLimit())
                    .memoryLimit(problem.getMemoryLimit())
                    .build();
        } else {
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
}
