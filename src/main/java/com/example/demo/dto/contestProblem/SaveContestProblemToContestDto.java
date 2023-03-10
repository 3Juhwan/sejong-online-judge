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
public class SaveContestProblemToContestDto {

    private Long contestId;
    private List<ProblemId> problemList;

    @Getter
    public static class ProblemId {
        Long problemId;
    }
}
