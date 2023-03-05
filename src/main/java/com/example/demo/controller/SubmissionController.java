package com.example.demo.controller;

import com.example.demo.dto.submission.SubmissionDto;
import com.example.demo.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import java.security.Principal;
import java.util.List;

import static com.example.demo.util.AuthUtil.allAuth;
import static com.example.demo.validation.AuthValidation.checkValidUsername;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping("/submit")
    @PreAuthorize(allAuth)
    public ResponseEntity<SubmissionDto> addSubmission(@Valid @RequestBody SubmissionDto submissionDto) {
        return ResponseEntity.ok(submissionService.saveSubmission(submissionDto));
    }

    /**
     * 주어진 쿼리에 따라 제출 조회
     * @param username
     * @param principal
     * @return ResponseEntity<SubmissionDto>
     */
    @GetMapping(value = "/submission", params = {"username"})
    @PreAuthorize(allAuth)
    public ResponseEntity<List<SubmissionDto>> getSubmissionByUser(@Valid @RequestParam String username, Principal principal) {
        try {
            checkValidUsername(principal, username);
            return ResponseEntity.ok(submissionService.getSubmissionByUsername(username));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Not Found");
        }
    }

//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    @GetMapping(value = "/submission", params = {"username"})
//    public ResponseEntity<SubmissionDto> getSubmissionByUserAndProblem(@Valid @RequestParam SubmissionDto submissionDto) {
//        return ResponseEntity.ok(submissionService.saveSubmission(submissionDto));
//    }
}
