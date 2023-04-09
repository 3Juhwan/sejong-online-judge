package com.example.demo.dto.contestProblem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveContestProblemDto {

    private Long contestId;
    private List<ProblemInfo> problemList;

    @Getter
    public static class ProblemInfo {
        Long problemId;
        Long sequence;
        String title;
    }
}
