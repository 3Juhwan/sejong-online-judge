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
    private Long courseId;
    private Long score;
    private Long total;

    public static FindCourseDto from(Course course) {
        return FindCourseDto.builder()
                .title(course.getTitle())
                .courseId(course.getId())
                .score(0L)
                .total(0L)
                .build();
    }
}
