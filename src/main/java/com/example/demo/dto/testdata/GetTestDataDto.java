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
public class GetTestDataDto {

    private Long testDataId;
    private String input;
    private String output;
    private Boolean hidden;

    public static GetTestDataDto from(@NotNull TestData testData) {
        return GetTestDataDto.builder()
                .testDataId(testData.getId())
                .input(testData.getInput())
                .output(testData.getOutput())
                .hidden(testData.getHidden())
                .build();
    }

}
