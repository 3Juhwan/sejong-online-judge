package com.example.demo.controller;

import com.example.demo.dto.course.SaveUserToCourseDto;
import com.example.demo.dto.course.CreateCourseDto;
import com.example.demo.dto.course.FindCourseDto;
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

    @PostMapping("/course/new")
    @PreAuthorize(allAuth)
    public ResponseEntity<Object> createCourse(@Valid @RequestBody CreateCourseDto courseDto) {
        courseService.saveCourse(courseDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/course/register")
    @PreAuthorize(professorAuth)
    public ResponseEntity<Object> saveUserToCourse(@Valid @RequestBody SaveUserToCourseDto courseDto) {
        courseService.saveUserToCourse(courseDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/courses")
    @PreAuthorize(allAuth)
    public ResponseEntity<List<FindCourseDto>> getCourses(Principal principal) {
        return ResponseEntity.ok(courseService.getCourses(principal));
    }
}
