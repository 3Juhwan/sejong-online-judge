package com.example.demo.service;

import com.example.demo.dto.CourseUserDto;
import com.example.demo.dto.course.AddUserToCourseDto;
import com.example.demo.dto.course.CourseDto;
import com.example.demo.dto.course.FindUserCourseDto;
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


    public CourseDto saveCourse(CourseDto courseDto) {
        User creator = userRepository.findByUsername(courseDto.getCreator()).get();
        Course course = CourseDto.toEntity(courseDto, creator);
        return CourseDto.from(courseRepository.save(course), courseDto.getCreator());
    }

    public CourseUserDto registerUserToCourse(AddUserToCourseDto courseDto) {
        courseDto.splitUsers();
        for (String user : courseDto.getUserList()) {
            User findUser = userRepository.findByUsername(user).get();
            Course findCourse = courseRepository.findById(courseDto.getCourseId()).get();
            CourseUser courseUser = new CourseUser(findUser, findCourse);
            courseUserRepository.save(courseUser);
        }
        return CourseUserDto.from(courseDto.getCourseId());
    }

    public List<FindUserCourseDto> findUserCourse(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).get();
        return courseUserRepository.findCourseUsersByUser(user)
                .stream()
                .map(c -> FindUserCourseDto.from(c.getCourse()))
                .collect(Collectors.toList());
    }

}
