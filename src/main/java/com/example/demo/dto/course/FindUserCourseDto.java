package com.example.demo.dto.course;

import com.example.demo.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindUserCourseDto {

    private String title;

    public static FindUserCourseDto from(Course course) {
        return FindUserCourseDto.builder()
                .title(course.getTitle())
                .build();
    }
}
