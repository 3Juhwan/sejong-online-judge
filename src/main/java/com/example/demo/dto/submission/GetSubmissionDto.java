package com.example.demo.dto.submission;

import com.example.demo.entity.Submission;
import com.example.demo.entity.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetSubmissionDto {

    private String username;
    private Long contestProblemId;
    private String code;
    private String language;
    private Long length;

//    public static Submission toEntity(GetSubmissionDto submissionDto) {
//
//    }

    public static GetSubmissionDto from(Submission submission) {
        return GetSubmissionDto.builder()
                .username(submission.getUser().getUsername())
                .contestProblemId(submission.getContestProblem().getId())
                .code(submission.getCode())
                .length(submission.getLength())
                .language(Language.find(submission.getLanguage()))
                .build();
    }

}
