package com.example.demo.service;

import com.example.demo.dto.course.*;
import com.example.demo.entity.Course;
import com.example.demo.entity.CourseUser;
import com.example.demo.entity.Language;
import com.example.demo.entity.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.CourseUserRepository;
import com.example.demo.repository.LanguageRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseUserRepository courseUserRepository;
    private final LanguageRepository languageRepository;


    public void saveCourse(CreateCourseDto courseDto, Principal principal) {
        User creator = userRepository.findByUsername(principal.getName()).orElse(null);
        Language setLanguage = languageRepository.save(new Language(courseDto.getLanguage()));
        Course course = CreateCourseDto.toEntity(courseDto, creator, setLanguage);
        courseRepository.save(course);
    }

    public void saveUserToCourse(SaveUserToCourseDto courseDto) {
        courseDto.splitUsers();
        for (String user : courseDto.getUserList()) {
            User findUser = userRepository.findByUsername(user).orElse(null);
            Course findCourse = courseRepository.findById(courseDto.getCourseId()).orElse(null);
            CourseUser courseUser = new CourseUser(findUser, findCourse);
            courseUserRepository.save(courseUser);
        }
    }

    public GetCourseDto getCourse(Long courseId) {
        return courseRepository.findCourseByCourseId(courseId).orElse(null);
    }

    public List<FindCourseDto> getCourses(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).orElse(null);
        return courseUserRepository.findCourseUsersByUser(user)
                .stream()
                .map(c -> FindCourseDto.from(c.getCourse()))
                .collect(Collectors.toList());
    }

    public void updateCourse(UpdateCourseDto courseDto) {
        Course course = courseRepository.findById(courseDto.getCourseId()).orElse(null);
        courseRepository.save(course.updateEntity(courseDto));
    }

}
