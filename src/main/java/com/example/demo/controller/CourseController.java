package com.example.demo.controller;

import com.example.demo.dto.course.*;
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
    @PreAuthorize(professorAuth)
    public ResponseEntity<CreateCourseDto> createCourse(@Valid @RequestBody CreateCourseDto courseDto, Principal principal) {
        return ResponseEntity.ok(courseService.saveCourse(courseDto, principal));
    }

    @PutMapping("/course")
    @PreAuthorize(professorAuth)
    public ResponseEntity<Object> updateCourse(@Valid @RequestBody UpdateCourseDto courseDto) {
        courseService.updateCourse(courseDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/course/register")
    @PreAuthorize(professorAuth)
    public ResponseEntity<Object> saveUserToCourse(@Valid @RequestBody SaveUserToCourseDto courseDto) {
        courseService.saveUserToCourse(courseDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/course-list")
    @PreAuthorize(allAuth)
    public ResponseEntity<List<FindCourseDto>> getCourses(Principal principal) {
        return ResponseEntity.ok(courseService.getCourses(principal));
    }

    @GetMapping(value = "/course", params = "courseId")
    public ResponseEntity<GetCourseDto> getOneCourse(@Valid @RequestParam Long courseId) {
        return ResponseEntity.ok(courseService.getCourse(courseId));
    }

    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }
}
