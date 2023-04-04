package com.example.demo.service;

import com.example.demo.dto.user.GetListOfCourseUserDto;
import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.CourseUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseUserService {

    private final CourseUserRepository courseUserRepository;
    private final CourseRepository courseRepository;

    public GetListOfCourseUserDto getListOfCourseUser(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        List<String> courseUserNameList = courseUserRepository.findAllCourseUsersByCourse(course).stream()
                .map(cu -> cu.getUser().getUsername()).toList();
        String ta = courseUserNameList.stream()
                .filter(c -> c.startsWith("TA"))
                .findFirst()
                .orElse(null);
        if (ta != null) {
            courseUserNameList.remove(ta);
        }
        return GetListOfCourseUserDto.builder()
                .studentList(String.join("\n", courseUserNameList))
                .teachingAssistantList(ta)
                .build();
    }

}
