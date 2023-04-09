package com.example.demo.dto.contestProblem;

import com.example.demo.entity.ContestProblem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveContestProblemResponseDto {

    private Long contestId;
    private List<ContestProblemInfo> problemList = new ArrayList<>();

    public SaveContestProblemResponseDto(Long contestId) {
        this.contestId = contestId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContestProblemInfo {
        Long contestProblemId;
        Long sequence;
        String title;

        public ContestProblemInfo(ContestProblem contestProblem) {
            this.contestProblemId = contestProblem.getId();
            this.sequence = contestProblem.getSequence();
            this.title = contestProblem.getTitle();
        }
    }

    public void addProblemInfo(ContestProblem contestProblem) {
        this.problemList.add(new ContestProblemInfo(contestProblem));
    }

}
