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
public class FindCourseDto {

    private String title;

    public static FindCourseDto from(Course course) {
        return FindCourseDto.builder()
                .title(course.getTitle())
                .build();
    }
}
