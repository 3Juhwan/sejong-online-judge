package com.example.demo.dto.course;

import com.example.demo.dto.language.GetLanguageListDto;
import lombok.Getter;

@Getter
public class UpdateCourseDto {

    private GetLanguageListDto language;
    private Long courseId;
    private String title;
    private String semester;

}
