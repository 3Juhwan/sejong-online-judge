package com.example.demo.dto.submission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionResponseDto {
        private Long dataSequence;
        private String inputData;
        private String outputData;
        private String result;
        private Long status;
        private Long usedMem;
        private Long usedTime;
}
