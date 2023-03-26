package com.example.demo.dto.course;

public interface GetCourseDto {
    Long getCourseId();

    String getTitle();

    String getSemester();

    Language getLanguage();

    interface Language {
        Boolean getPython();

        Boolean getC();

        Boolean getCpp();

        Boolean getJava();
    }
}
