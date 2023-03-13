package com.example.demo.dto.contestProblem;

import com.example.demo.dto.submitstatus.GetSubmitStatusDto;
import com.example.demo.entity.ContestProblem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetContestProblemDto {

    private Long sequence;

    private String title;

    private Long problemId;

    private GetSubmitStatusDto submitStatusDto;


    public static GetContestProblemDto from(ContestProblem contestProblem, GetSubmitStatusDto submitStatusDto) {
        return GetContestProblemDto.builder()
                .sequence(contestProblem.getSequence())
                .title(contestProblem.getTitle())
                .problemId(contestProblem.getProblem().getId())
                .submitStatusDto(submitStatusDto)
                .build();
    }

//    public static ContestProblem toEntity(GetContestProblemDto contestProblemDto) {
//        return ContestProblem.builder()
//                .
//                .build();
//    }
}
