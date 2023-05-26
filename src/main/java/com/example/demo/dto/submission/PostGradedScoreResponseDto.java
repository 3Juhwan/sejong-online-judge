package com.example.demo.dto.submission;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostGradedScoreResponseDto {
    private Long submissionId;
    private Long gradedScore;
}
