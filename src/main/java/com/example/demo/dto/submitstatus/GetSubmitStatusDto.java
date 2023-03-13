package com.example.demo.dto.submitstatus;

import com.example.demo.entity.SubmitStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetSubmitStatusDto {

    private Long submitCnt;
    private Long highScore;


    public static GetSubmitStatusDto from(SubmitStatus submitStatus) {
        return GetSubmitStatusDto.builder()
                .submitCnt(submitStatus.getSubmitCnt())
                .highScore(submitStatus.getHighScore())
                .build();
    }

    public static SubmitStatus toEntity(GetSubmitStatusDto submitStatusDto) {
        return SubmitStatus.builder()
                .submitCnt(submitStatusDto.getSubmitCnt())
                .highScore(submitStatusDto.getHighScore())
                .build();
    }

}
