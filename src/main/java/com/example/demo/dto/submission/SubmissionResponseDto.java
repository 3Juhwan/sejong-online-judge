package com.example.demo.dto.submission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionResponseDto {
        Long dataSequence;
        String inputData;
        String outputData;
        String result;
        Long status;
        Long usedMem;
        Long usedTime;
}
