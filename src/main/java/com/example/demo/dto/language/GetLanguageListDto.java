package com.example.demo.dto.language;

import com.example.demo.entity.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetLanguageListDto {

    private Boolean c;
    private Boolean cpp;
    private Boolean java;
    private Boolean python;

    public static GetLanguageListDto from(Language language) {
        return GetLanguageListDto.builder()
                .c(language.getC())
                .cpp(language.getCpp())
                .java(language.getJava())
                .python(language.getPython())
                .build();
    }
}
