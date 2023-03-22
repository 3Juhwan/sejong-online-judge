package com.example.demo.dto.contestProblem;

import com.example.demo.dto.submitstatus.GetSubmitStatusByUserDto;
import com.example.demo.entity.ContestProblem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetContestProblemDto {

    private Long sequence;
    private String title;
    private Long problemId;
    private GetSubmitStatusByUserDto submitStatusDto;


    public static GetContestProblemDto from(ContestProblem contestProblem, GetSubmitStatusByUserDto submitStatusDto) {
        return GetContestProblemDto.builder()
                .sequence(contestProblem.getSequence())
                .title(contestProblem.getTitle())
                .problemId(contestProblem.getProblem().getId())
                .submitStatusDto(submitStatusDto)
                .build();
    }
}
