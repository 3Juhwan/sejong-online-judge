package com.example.demo.dto;

import com.example.demo.entity.Problem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDto {

    private String title;

    private String content;

    private Long timeLimit;

    private Long memoryLimit;

    private List<TestDataDto> testDataDtoList;

    public static ProblemDto from(Problem problem) {
        if (problem == null) return null;

        return ProblemDto.builder()
                .title(problem.getTitle())
                .content(problem.getContent())
                .timeLimit(problem.getTimeLimit())
                .memoryLimit(problem.getMemoryLimit())
//                .testDataDtoList()
                .build();
    }
}
