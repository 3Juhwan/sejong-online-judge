package com.example.demo.dto;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseUser;
import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseUserDto {

    private Long id;

    private Long userId;

    private Long courseId;

    public static CourseUser toEntity(@NotNull CourseUserDto courseUserDto, User user, Course course) {
        return CourseUser.builder()
//                .course(course)
//                .user(user)
                .build();
    }

    public static CourseUserDto from(Long courseId) {
        return CourseUserDto.builder()
                .courseId(courseId)
                .build();
    }
}
