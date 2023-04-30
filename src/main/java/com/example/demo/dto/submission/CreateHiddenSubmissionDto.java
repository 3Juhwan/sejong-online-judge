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
public class CreateHiddenSubmissionDto {
    @NotNull
    private Long contestProblemId;
    @NotNull
    private String code;


    public static CreateHiddenSubmissionDto from(Submission submission) {
        if (submission == null) {
            return null;
        }

        return CreateHiddenSubmissionDto.builder()
                .code(submission.getCode())
                .build();
    }

    public static Submission toEntity(CreateHiddenSubmissionDto submissionDto, User user, ContestProblem contestProblem, Problem problem) {
        return Submission.builder()
                .user(user)
                .problem(problem)
                .contestProblem(contestProblem)
                .code(submissionDto.getCode())
                .length((long) submissionDto.getCode().length())
                .build();
    }
}