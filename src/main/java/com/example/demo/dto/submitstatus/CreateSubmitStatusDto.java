package com.example.demo.dto.submitstatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubmitStatusDto {

    private String title;

    private Long problemId;

    private Long contestId;

//    public static SubmitStatus toEntity(SavingSubmitStatusDto submitStatusDto) {
//        return SubmitStatus.builder()
//                .owner()
//                .build();
//    }


}
