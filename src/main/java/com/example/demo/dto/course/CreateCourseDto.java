package com.example.demo.dto.course;

import com.example.demo.entity.Course;
import com.example.demo.entity.Language;
import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseDto {

    private String title;
    private String creator;
    private Long linkedCourseId;
    private String semester;
    private String language;


    public static Course toEntity(CreateCourseDto courseDto, User creator, Language language) {
        return Course.builder()
                .title(courseDto.title)
                .creator(creator)
                .linkedCourseId(courseDto.linkedCourseId)
                .semester(courseDto.semester)
                .language(language)
                .build();
    }

    public static CreateCourseDto from(Course course) {
        return CreateCourseDto.builder()
                .creator(course.getCreator().getUsername())
                .title(course.getTitle())
                .semester(course.getSemester())
                .linkedCourseId(course.getLinkedCourseId())
                .language(course.getLanguage().toString())
                .build();
    }
}
