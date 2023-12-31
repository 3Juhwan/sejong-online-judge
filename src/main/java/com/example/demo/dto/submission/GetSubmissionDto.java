package com.example.demo.dto.submission;

import com.example.demo.entity.Submission;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.Principal;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetSubmissionDto {

    private Long submissionId;
    private String username;
    private Long contestProblemId;
    private String title;
    private Long status;
    private Long score;
    private Long memoryUsage;
    private Long timeUsage;
    private String language;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean readable;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sourceCode;
    private Long length;
    private LocalDateTime submitTime;

    public static GetSubmissionDto from(Submission submission, Principal principal, boolean isHidden) {
        GetSubmissionDtoBuilder tmp = GetSubmissionDto.builder()
                .submissionId(submission.getId())
                .username(submission.getUser().getUsername())
                .contestProblemId(submission.getContestProblem().getId())
                .title(submission.getContestProblem().getTitle())
                .score(submission.getScore())
                .memoryUsage(submission.getMemoryUsage())
                .language("python")
                .timeUsage(submission.getTimeUsage())
                .length(submission.getLength())
                .status(submission.getStatus())
                .submitTime(submission.getSubmitTime());

        if (isHidden) {
            return tmp.sourceCode(submission.getCode()).build();
        } else {
            return tmp.readable(principal.getName().equals(submission.getUser().getUsername())).build();
        }
    }

}
