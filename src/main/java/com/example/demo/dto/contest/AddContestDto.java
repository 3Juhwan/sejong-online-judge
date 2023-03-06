package com.example.demo.dto.contest;

import com.example.demo.entity.Contest;
import com.example.demo.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddContestDto {

    private String title;
    private Long courseId;

    public static Contest toEntity(AddContestDto contestDto, Course course) {
        return Contest.builder()
                .title(contestDto.title)
                .course(course)
                .build();
    }

    public static AddContestDto from(Contest contest) {
        return AddContestDto.builder()
                .title(contest.getTitle())
                .courseId(contest.getCourse().getId())
                .build();
    }
}
