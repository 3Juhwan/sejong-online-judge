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
public class SaveTestDataDto {

    private Long testDataId;
    private Long problemId;
    private String input;
    private String output;
    private Boolean hidden;

    public static SaveTestDataDto from(@NotNull TestData testData) {
        return SaveTestDataDto.builder()
                .testDataId(testData.getId())
                .problemId(testData.getProblem().getId())
                .input(testData.getInput())
                .output(testData.getOutput())
                .hidden(testData.getHidden())
                .build();
    }

    public static TestData toEntity(@NotNull SaveTestDataDto testDataDto) {
        return TestData.builder()
                .input(testDataDto.getInput())
                .output(testDataDto.getOutput())
                .build();
    }
}
