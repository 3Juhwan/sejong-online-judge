package com.example.demo.dto.grade;

import com.example.demo.entity.Contest;
import com.example.demo.entity.Grade;
import com.example.demo.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostGradedScoreBoardRequestDto {

    private Long contestId;

    private String referenceTime;

    public static Grade toEntity(PostGradedScoreBoardRequestDto requestDto, Contest contest, User user) {
        return Grade.builder()
                .contest(contest)
                .creator(user)
                .referenceTime(LocalDateTime.parse(requestDto.referenceTime))
                .build();
    }
}
