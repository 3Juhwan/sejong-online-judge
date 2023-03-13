package com.example.demo.dto.submission;

import com.example.demo.entity.ContestProblem;
import com.example.demo.entity.Submission;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSubmissionDto {


    // Submit Info
    private String username;

    @NotNull
    private Long contestProblemId;

    @NotNull
    private String code;

    @NotNull
    private String language;


    public static CreateSubmissionDto from(Submission submission) {
        if (submission == null) {
            return null;
        }

        return CreateSubmissionDto.builder()
                .username(submission.getUser().getUsername())
                .code(submission.getCode())
                .language(submission.getLanguage().name())
                .build();
    }

    public static Submission toEntity(CreateSubmissionDto submissionDto, User user, ContestProblem contestProblem) {
        return Submission.builder()
                .user(user)
                .contestProblem(contestProblem)
                .code(submissionDto.getCode())
                .language(Language.find(submissionDto.getLanguage()))
                .build();
    }
}