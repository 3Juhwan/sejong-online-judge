package com.example.demo.dto.contest;

import com.example.demo.entity.Contest;
import com.example.demo.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateContestDto {

    private String title;
    private Long courseId;
    private String startingTime;
    private String endingTime;


    public static Contest toEntity(CreateContestDto contestDto, Course course) {
        return Contest.builder()
                .title(contestDto.title)
                .course(course)
                .startingTime(LocalDateTime.parse(contestDto.startingTime))
                .endingTime(LocalDateTime.parse(contestDto.endingTime))
                .build();
    }

    public static CreateContestDto from(Contest contest) {
        return CreateContestDto.builder()
                .title(contest.getTitle())
                .courseId(contest.getCourse().getId())
                .build();
    }
}
