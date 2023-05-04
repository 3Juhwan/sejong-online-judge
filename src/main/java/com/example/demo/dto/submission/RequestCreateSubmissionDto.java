package com.example.demo.dto.submission;

import com.example.demo.entity.ContestProblem;
import com.example.demo.entity.Problem;
import com.example.demo.entity.Submission;
import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCreateSubmissionDto {
    @NotNull
    private Long contestProblemId;
    @NotNull
    private String code;


    public static RequestCreateSubmissionDto from(Submission submission) {
        if (submission == null) {
            return null;
        }

        return RequestCreateSubmissionDto.builder()
                .code(submission.getCode())
                .build();
    }

    public static Submission toEntity(RequestCreateSubmissionDto submissionDto, User user, ContestProblem contestProblem, Problem problem, Long status, String result) {
        return Submission.builder()
                .user(user)
                .problem(problem)
                .contestProblem(contestProblem)
                .code(submissionDto.getCode())
                .length((long) submissionDto.getCode().length())
                .status(status)
                .result(result)
                .build();
    }
}