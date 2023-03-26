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
public class CreateContestDto {

    private String title;
    private Long courseId;
    private String startingTime;
    private String endingTime;


    public static Contest toEntity(CreateContestDto contestDto, Course course) {
        Contest build = Contest.builder()
                .title(contestDto.title)
                .course(course)
                .build();
        return build.setDateTime(contestDto.startingTime, contestDto.endingTime);
    }
}
