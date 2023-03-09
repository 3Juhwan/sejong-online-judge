package com.example.demo.dto.course;

import com.example.demo.entity.Course;
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


    public static Course toEntity(CreateCourseDto courseDto, User creator) {
        return Course.builder()
                .title(courseDto.title)
                .creator(creator)
                .linkedCourseId(courseDto.linkedCourseId)
                .build();
    }

    public static CreateCourseDto from(Course course) {
        return CreateCourseDto.builder()
                .title(course.getTitle())
                .creator(course.getCreator().getUsername())
                .linkedCourseId(course.getLinkedCourseId())
                .build();
    }
}
