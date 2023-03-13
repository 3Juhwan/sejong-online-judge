package com.example.demo.dto.submission;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetSubmissionDto {

    private String username;
    private Long contestProblem;

    private Problem problem;

    @NotNull
    private String code;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Language language;

    private Long length;

//    public static Submission toEntity(GetSubmissionDto submissionDto) {
//
//    }

}
