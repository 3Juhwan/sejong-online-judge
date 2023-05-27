package com.example.demo.service;

import com.example.demo.dto.course.CreateCourseDto;
import com.example.demo.dto.course.FindCourseDto;
import com.example.demo.dto.course.GetCourseDto;
import com.example.demo.dto.course.UpdateCourseDto;
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


    public CreateCourseDto saveCourse(CreateCourseDto courseDto, Principal principal) {
        User creator = userRepository.findByUsername(principal.getName()).orElse(null);
        Language setLanguage = languageRepository.save(new Language(courseDto.getLanguage()));
        Course savedCourse = courseRepository.save(CreateCourseDto.toEntity(courseDto, creator, setLanguage));
        courseUserRepository.save(new CourseUser(creator, savedCourse));    // 교수 본인 등록
        return CreateCourseDto.from(savedCourse);
    }

    public GetCourseDto getCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        return GetCourseDto.from(course, course.getLanguage());
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

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

}
