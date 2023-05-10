package com.example.demo.dto.submission;

import com.example.demo.entity.Submission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTotalSubmissionDto {

    private String username;
    private List<submissionDto> submissionList = new ArrayList<>();

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class submissionDto {
        private Long contestProblemId;
        private Long submissionId;
        private Long score;

        public static submissionDto from(Submission submission) {
            if(submission == null) {
                return new submissionDto();
            }
            return submissionDto.builder()
                    .submissionId(submission.getId())
                    .score(submission.getScore())
                    .contestProblemId(submission.getContestProblem().getId())
                    .build();
        }
    }

    public void addItem(Submission submission) {
        submissionList.add(submissionDto.from(submission));
    }
}
