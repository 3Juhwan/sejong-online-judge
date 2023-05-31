package com.example.demo.dto.contestProblem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveContestProblemDto {
    private Long contestId;
    private Long problemId;
    private Long sequence;
    private String title;
}
