package com.example.demo.dto.language;

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
}
