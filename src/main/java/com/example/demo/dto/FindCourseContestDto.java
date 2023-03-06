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
public class FindCourseContestDto {

    private Long id;
    private String title;

    public static FindCourseContestDto from(Contest contest) {
        return FindCourseContestDto.builder()
                .id(contest.getId())
                .title(contest.getTitle())
                .build();
    }
}
