package com.example.demo.controller;

import com.example.demo.dto.CourseUserDto;
import com.example.demo.dto.course.AddUserToCourseDto;
import com.example.demo.dto.course.CourseDto;
import com.example.demo.dto.course.FindUserCourseDto;
import com.example.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static com.example.demo.util.AuthUtil.allAuth;
import static com.example.demo.util.AuthUtil.professorAuth;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/new-course")
    @PreAuthorize(allAuth)
    public ResponseEntity<CourseDto> addCourse(@Valid @RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.saveCourse(courseDto));
    }

    @PostMapping("/course/register-user")
    @PreAuthorize(professorAuth)
    public ResponseEntity<CourseUserDto> addUserToCourse(@Valid @RequestBody AddUserToCourseDto courseDto) {
        return ResponseEntity.ok(courseService.registerUserToCourse(courseDto));
    }

    @GetMapping("/mainpage")
    @PreAuthorize(allAuth)
    public ResponseEntity<List<FindUserCourseDto>> getUserCourse(Principal principal) {
        return ResponseEntity.ok(courseService.findUserCourse(principal));
    }
}
