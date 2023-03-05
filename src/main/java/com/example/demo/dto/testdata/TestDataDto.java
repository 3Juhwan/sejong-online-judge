package com.example.demo.dto.testdata;

import com.example.demo.entity.TestData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestDataDto {

    private Long id;

    private Long problemId;

    private String input;

    private String output;

    public static TestDataDto from(@NotNull TestData testData) {
        return TestDataDto.builder()
                .input(testData.getInput())
                .output(testData.getOutput())
                .build();
    }

    public static TestData toEntity(@NotNull TestDataDto testDataDto) {
        return TestData.builder()
                .input(testDataDto.getInput())
                .output(testDataDto.getOutput())
                .build();
    }
}
