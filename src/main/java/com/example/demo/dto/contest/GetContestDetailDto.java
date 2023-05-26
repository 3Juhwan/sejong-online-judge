package com.example.demo.dto.contest;

import com.example.demo.dto.contestProblem.GetContestProblemByContestDto;
import com.example.demo.entity.SubmitStatus;
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
    List<ContestProblemDto> contestProblemDtoList;

    public void from(GetContestProblemByContestDto contestProblemDto, SubmitStatus submitStatus) {
        ContestProblemDto contestProblem = ContestProblemDto.builder()
                .title(contestProblemDto.getTitle())
                .sequence(contestProblemDto.getSequence())
                .problemId(contestProblemDto.getProblemId())
                .contestProblemId(contestProblemDto.getContestProblemId())
                .highScore(submitStatus.getHighScore())
                .submitCnt(submitStatus.getSubmitCnt())
                .build();
        this.contestProblemDtoList.add(contestProblem);
    }

    @Getter
    @Builder
    public static class ContestProblemDto {
        String title;
        Long sequence;
        Long problemId;
        Long contestProblemId;
        Long highScore;
        Long submitCnt;
    }

}
