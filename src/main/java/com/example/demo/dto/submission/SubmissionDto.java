package com.example.demo.dto.submission;

import com.example.demo.entity.enums.Status;
import com.example.demo.entity.Submission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionDto {


    // Submit Info
    private String username;

    @NotNull
    private Long problemId;

    @NotNull
    private String code;

    @NotNull
    private String language;

    //    @CreatedDate
    private LocalDateTime submitTime;


    // Submit Result
    private Long timeUsage;

    private Long memoryUsage;

    private Status status;

    public static SubmissionDto from(Submission submission) {
        if (submission == null) {
            return null;
        }

        return SubmissionDto.builder()
                .username(submission.getUser().getUsername())
                .problemId(submission.getProblem().getId())
                .code(submission.getCode())
                .language(submission.getLanguage().name())
                .timeUsage(submission.getTimeUsage())
                .memoryUsage(submission.getMemoryUsage())
                .status(submission.getStatus())
                .submitTime(submission.getSubmitTime())
                .build();
    }
}