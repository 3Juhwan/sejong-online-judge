package com.example.demo.dto.courseuser;

import lombok.Getter;

@Getter
public class SaveCourseUserDto {
    private Long courseId;
    private String studentList;
    private String teachingAssistantList;
}
