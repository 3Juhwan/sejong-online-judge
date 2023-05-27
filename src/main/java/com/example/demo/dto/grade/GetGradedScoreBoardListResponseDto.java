package com.example.demo.dto.grade;

import com.example.demo.entity.Contest;
import com.example.demo.entity.Grade;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetGradedScoreBoardListResponseDto {

    private Long gradeId;
    private Long contestId;
    private String contestTitle;
    private LocalDateTime referenceTime;

    public static GetGradedScoreBoardListResponseDto from(Grade grade, Contest contest) {
        return GetGradedScoreBoardListResponseDto.builder()
                .gradeId(grade.getId())
                .contestId(contest.getId())
                .contestTitle(contest.getTitle())
                .referenceTime(grade.getReferenceTime())
                .build();
    }
}
