package com.example.demo.dto.postBox;

import com.example.demo.entity.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostBoxRequestDto {

    private Long contestProblemId;
    private Long courseId;
    private Long contestId;

    public static PostBox toEntity(CreatePostBoxRequestDto requestDto, ContestProblem contestProblem, Course course, Contest contest, User user) {
        return PostBox.builder()
                .contestProblem(contestProblem)
                .user(user)
                .contest(contest)
                .course(course)
                .build();
    }
}
