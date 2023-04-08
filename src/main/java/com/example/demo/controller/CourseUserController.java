package com.example.demo.controller;

import com.example.demo.dto.courseuser.SaveCourseUserDto;
import com.example.demo.dto.courseuser.SaveCourseUserResponseDto;
import com.example.demo.dto.user.GetListOfCourseUserDto;
import com.example.demo.service.CourseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.demo.util.AuthUtil.professorAuth;
import static com.example.demo.util.AuthUtil.studentExclusiveAuth;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CourseUserController {

    private final CourseUserService courseUserService;

    @PostMapping("/course-user")
    @PreAuthorize(professorAuth)
    public ResponseEntity<SaveCourseUserResponseDto> saveCourseUser(@Valid @RequestBody SaveCourseUserDto courseDto) {
        return ResponseEntity.ok(courseUserService.saveCourseUser(courseDto));
    }

    @GetMapping(value = "/course-user", params = {"courseId"})
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<GetListOfCourseUserDto> getListOfCourseUser(@Valid @RequestParam Long courseId) {
        return ResponseEntity.ok(courseUserService.getListOfCourseUser(courseId));
    }

}
