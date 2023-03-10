package com.example.demo.dto;

import com.example.demo.entity.Contest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCourseContestDto {

    private Long contestId;
    private String title;

    public static GetCourseContestDto from(Contest contest) {
        return GetCourseContestDto.builder()
                .contestId(contest.getId())
                .title(contest.getTitle())
                .build();
    }
}
