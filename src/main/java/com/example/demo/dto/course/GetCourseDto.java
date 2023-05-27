package com.example.demo.dto.course;

import com.example.demo.entity.Course;
import com.example.demo.entity.Language;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCourseDto {
    private Long courseId;
    private String title;
    private String semester;
    private LanguageDto language;

    public static GetCourseDto from(Course course, Language language) {
        return GetCourseDto.builder()
                .courseId(course.getId())
                .title(course.getTitle())
                .semester(course.getSemester())
                .language(LanguageDto.from(language))
                .build();
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static class LanguageDto {
        private Boolean python;
        private Boolean c;
        private Boolean cpp;
        private Boolean java;

        public static LanguageDto from(Language language) {
            return LanguageDto.builder()
                    .c(language.getC())
                    .cpp(language.getCpp())
                    .python(language.getPython())
                    .java(language.getJava())
                    .build();
        }
    }
}
