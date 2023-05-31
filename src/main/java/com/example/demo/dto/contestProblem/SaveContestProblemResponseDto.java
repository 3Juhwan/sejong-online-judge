package com.example.demo.dto.contestProblem;

import com.example.demo.entity.ContestProblem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveContestProblemResponseDto {
    private Long contestId;
    private Long contestProblemId;
    private Long sequence;
    private String title;

    public static SaveContestProblemResponseDto from(ContestProblem contestProblem) {
        return SaveContestProblemResponseDto.builder()
                .contestId(contestProblem.getContest().getId())
                .contestProblemId(contestProblem.getId())
                .sequence(contestProblem.getSequence())
                .title(contestProblem.getTitle())
                .build();
    }
}
