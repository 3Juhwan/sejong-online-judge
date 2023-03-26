package com.example.demo.controller;

import com.example.demo.dto.submission.CreateSubmissionDto;
import com.example.demo.dto.submission.GetSubmissionDto;
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
import java.util.Objects;

import static com.example.demo.util.AuthUtil.allAuth;
import static com.example.demo.validation.AuthValidation.checkValidUsername;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping("/submission/new")
    @PreAuthorize(allAuth)
    public ResponseEntity<Objects> createSubmission(@Valid @RequestBody CreateSubmissionDto submissionDto) {
        submissionService.saveSubmission(submissionDto);
        return ResponseEntity.noContent().build();
    }

    /**
     * 주어진 쿼리에 따라 제출 조회
     *
     * @param username
     * @param principal
     * @return ResponseEntity<SubmissionDto>
     */
    @GetMapping(value = "/submission", params = {"username"})
    @PreAuthorize(allAuth)
    public ResponseEntity<List<CreateSubmissionDto>> getSubmissionByUser(@Valid @RequestParam String username, Principal principal) {
        try {
            checkValidUsername(principal, username);
            return ResponseEntity.ok(submissionService.getSubmissionByUsername(username));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Not Found");
        }
    }

    @GetMapping(value = "/submission", params = {"username", "contestProblemId"})
    @PreAuthorize(allAuth)
    public ResponseEntity<List<GetSubmissionDto>> getSubmissions(@Valid @RequestParam String username, @Valid @RequestParam Long contestProblemId) {
        return ResponseEntity.ok(submissionService.getSubmissionByUserAndProblem(username, contestProblemId));
    }

}
