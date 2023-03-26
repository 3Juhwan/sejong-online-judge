package com.example.demo.dto.course;

import lombok.Getter;

@Getter
public class UpdateCourseDto {

    Language language;
    private Long courseId;
    private String title;
    private String semester;

    @Getter
    static public class Language {
        Boolean python;
        Boolean c;
        Boolean cpp;
        Boolean java;
    }

}
