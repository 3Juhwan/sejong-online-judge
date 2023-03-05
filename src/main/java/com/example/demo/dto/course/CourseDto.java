package com.example.demo.dto.course;

import com.example.demo.entity.Contest;
import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private Long id;

    private String title;

    private Long linkedCourseId;

    private String registeringUsers;

    private String creator;

    private String registeredUsers;

    public static Course toEntity(@NotNull CourseDto courseDto, User creator) {
        return Course.builder()
                .id(courseDto.id)
                .title(courseDto.title)
                .creator(creator)
                .linkedCourseId(courseDto.linkedCourseId)
                .build();
    }

    public static Course toEntity(@NotNull CourseDto courseDto, User creator, List<User> users) {
        return Course.builder()
                .id(courseDto.id)
                .title(courseDto.title)
                .creator(creator)
                .linkedCourseId(courseDto.linkedCourseId)
                .build();
    }

    public static CourseDto from(Course course, String creator) {
        return CourseDto.builder()
                .title(course.getTitle())
                .creator(creator)
                .linkedCourseId(course.getLinkedCourseId())
                .build();
    }
}
