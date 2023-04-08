package com.example.demo.dto.courseuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCourseUserDto {

    String studentList;
    String teachingAssistantList;

}
