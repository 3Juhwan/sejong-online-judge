package com.example.demo.dto.submission;

import com.example.demo.entity.Submission;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetGradedScoreBoardResponseDto {

    private String username;
    private List<GetGradedScoreBoardResponseDto.submissionDto> submissionList = new ArrayList<>();

    public void addItem(Submission submission) {
        submissionList.add(GetGradedScoreBoardResponseDto.submissionDto.from(submission));
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class submissionDto {
        private Long contestProblemId;
        private Long submissionId;
        private Long score;
        private Long gradedScore;

        public static GetGradedScoreBoardResponseDto.submissionDto from(Submission submission) {
            if (submission == null) {
                return new GetGradedScoreBoardResponseDto.submissionDto();
            }
            return GetGradedScoreBoardResponseDto.submissionDto.builder()
                    .submissionId(submission.getId())
                    .score(submission.getScore())
                    .contestProblemId(submission.getContestProblem().getId())
                    .gradedScore(submission.getGradedScore())
                    .build();
        }
    }

}
