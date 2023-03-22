package com.example.demo.dto.contest;

import com.example.demo.dto.contestProblem.GetContestProblemByContestDto;
import com.example.demo.dto.submitstatus.GetSubmitStatusByUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetContestDetailDto {

    String title;
    LocalDateTime startingTime;
    LocalDateTime endingTime;
    List<ContestProblem> contestProblemList;

    public void from(GetContestProblemByContestDto contestProblemDto, GetSubmitStatusByUserDto submitStatusDto) {
        ContestProblem contestProblem = ContestProblem.builder()
                .title(contestProblemDto.getTitle())
                .sequence(contestProblemDto.getSequence())
                .problemId(contestProblemDto.getProblemId())
                .contestProblemId(contestProblemDto.getContestProblemId())
                .highScore(submitStatusDto.getHighScore())
                .submitCnt(submitStatusDto.getSubmitCnt())
                .build();

        this.contestProblemList.add(contestProblem);
    }

    @Getter     // 이거 없으면 spring이 알아서 json으로 바꾸지 못함...
    @Builder
    public static class ContestProblem {
        String title;
        Long sequence;
        Long problemId;
        Long contestProblemId;
        Long highScore;
        Long submitCnt;
    }
}
