//package com.example.demo.controller;
//
//import com.example.demo.dto.ProblemDto;
//import com.example.demo.dto.SubmissionDto;
//import com.example.demo.service.SubmissionService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/api/v1")
//@RequiredArgsConstructor
//public class SubmissionController {
//
//    private final SubmissionService submissionService;
//
//    @GetMapping("/submission")
////    @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    public ResponseEntity<SubmissionDto> getMyUserInfo(@Valid @RequestBody ProblemDto submissionDto) {
//        return ResponseEntity.ok(submissionService.saveSubmission(submissionDto));
//    }
//}
