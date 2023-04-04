package com.example.demo.dto.contest;

import com.example.demo.entity.Contest;
import com.example.demo.entity.Course;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateContestDto {

    private Long contestId;
    private String title;
    private Long courseId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String startingTime;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
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

    public static CreateContestDto from(Contest contest) {
        return CreateContestDto.builder()
                .title(contest.getTitle())
                .contestId(contest.getId())
                .courseId(contest.getCourse().getId())
                .sequence(contest.getSequence())
                .build();
    }
}
