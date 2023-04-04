package com.example.demo.controller;

import com.example.demo.dto.user.GetListOfCourseUserDto;
import com.example.demo.service.CourseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.example.demo.util.AuthUtil.studentExclusiveAuth;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CourseUserController {

    private final CourseUserService courseUserService;

    @GetMapping(value = "/course-user", params = {"courseId"})
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<GetListOfCourseUserDto> getListOfCourseUser(@Valid @RequestParam Long courseId) {
        return ResponseEntity.ok(courseUserService.getListOfCourseUser(courseId));
    }
}
