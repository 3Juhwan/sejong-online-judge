package com.example.demo.service;

import com.example.demo.dto.course.CreateCourseDto;
import com.example.demo.dto.course.FindCourseDto;
import com.example.demo.dto.course.SaveUserToCourseDto;
import com.example.demo.entity.Course;
import com.example.demo.entity.CourseUser;
import com.example.demo.entity.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.CourseUserRepository;
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


    public void saveCourse(CreateCourseDto courseDto) {
        User creator = userRepository.findByUsername(courseDto.getCreator()).get();
        Course course = CreateCourseDto.toEntity(courseDto, creator);
        courseRepository.save(course);
    }

    public void saveUserToCourse(SaveUserToCourseDto courseDto) {
        courseDto.splitUsers();
        for (String user : courseDto.getUserList()) {
            User findUser = userRepository.findByUsername(user).get();
            Course findCourse = courseRepository.findById(courseDto.getCourseId()).get();
            CourseUser courseUser = new CourseUser(findUser, findCourse);
            courseUserRepository.save(courseUser);
        }
    }

    public List<FindCourseDto> getCourses(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).get();
        return courseUserRepository.findCourseUsersByUser(user)
                .stream()
                .map(c -> FindCourseDto.from(c.getCourse()))
                .collect(Collectors.toList());
    }

}
