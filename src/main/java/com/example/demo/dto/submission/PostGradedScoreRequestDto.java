package com.example.demo.dto.submission;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostGradedScoreRequestDto {
    private Long submissionId;
    private Long gradedScore;
    private String comment;
}
