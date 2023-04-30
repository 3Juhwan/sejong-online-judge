package com.example.demo.dto.submission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSampleSubmissionDto {

    private Long contestProblemId;
    @NotNull
    private String sourceCode;
    private List<SampleCase> sampleCaseList = new ArrayList<>();

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SampleCase {
        Long dataSequence;
        String inputData;
        String outputData;
    }
}
