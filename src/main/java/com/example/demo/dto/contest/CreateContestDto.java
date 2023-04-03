package com.example.demo.dto.contest;

import com.example.demo.entity.Contest;
import com.example.demo.entity.Course;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateContestDto {

    private String title;
    private Long courseId;
    private String startingTime;
    private String endingTime;
    private Long sequence;


    public static Contest toEntity(CreateContestDto contestDto, Course course) {
        Contest build = Contest.builder()
                .title(contestDto.title)
                .course(course)
                .sequence(contestDto.sequence)
                .build();
        return build.setDateTime(contestDto.startingTime, contestDto.endingTime);
    }
}
