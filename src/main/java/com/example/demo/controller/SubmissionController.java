package com.example.demo.controller;

import com.example.demo.dto.submission.CreateSampleSubmissionDto;
import com.example.demo.dto.submission.GetSubmissionDto;
import com.example.demo.dto.submission.SubmissionResponseDto;
import com.example.demo.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static com.example.demo.util.AuthUtil.allAuth;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping("/submission/sample/new")
    @PreAuthorize(allAuth)
    public ResponseEntity<List<SubmissionResponseDto>> saveSampleSubmission(@Valid @RequestBody CreateSampleSubmissionDto submissionDto) {
        return ResponseEntity.ok(submissionService.getSampleSubmission(submissionDto));
    }

    @PostMapping("/submission/hidden/new")
    @PreAuthorize(allAuth)
    public ResponseEntity<List<SubmissionResponseDto>> saveHiddenSubmission(@Valid @RequestBody CreateSampleSubmissionDto submissionDto, Principal principal) {
        return ResponseEntity.ok(submissionService.getHiddenSubmission(submissionDto, principal));
    }

    @GetMapping(value = "/submission")
    @PreAuthorize(allAuth)
    public ResponseEntity<Page<GetSubmissionDto>> getSubmissionList(@Valid @RequestParam(value = "username", required = false) String username,
                                                                    @Valid @RequestParam(value = "contestProblemId", required = false) Long contestProblemId,
                                                                    @Valid @RequestParam(value = "status", required = false) String status,
                                                                    Principal principal, @PageableDefault(page = 0, size = 10, sort = "submitTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(submissionService.getSubmissionByCondition(principal, contestProblemId, username, status, pageable));
    }

    @GetMapping(value = "/sourcecode")
    @PreAuthorize(allAuth)
    public ResponseEntity<GetSubmissionDto> getSourceCode(@Valid @RequestParam(value = "submissionId") Long submissionId, Principal principal) {
        return ResponseEntity.ok(submissionService.getSubmissionWithSourceCode(principal, submissionId));
    }

}
