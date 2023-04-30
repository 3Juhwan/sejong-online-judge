package com.example.demo.controller;

import com.example.demo.dto.submission.CreateSampleSubmissionDto;
import com.example.demo.dto.submission.GetSubmissionDto;
import com.example.demo.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static com.example.demo.util.AuthUtil.allAuth;
//import static com.example.demo.validation.AuthValidation.checkValidUsername;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping("/submission/sample/new")
    @PreAuthorize(allAuth)
    public ResponseEntity<Object> saveSampleSubmission(@Valid @RequestBody CreateSampleSubmissionDto submissionDto) {
        return ResponseEntity.ok(submissionService.getSampleSubmission(submissionDto));
    }

    @PostMapping("/submission/hidden/new")
    @PreAuthorize(allAuth)
    public ResponseEntity<Object> saveHiddenSubmission(@Valid @RequestBody CreateSampleSubmissionDto submissionDto, Principal principal) {
        return ResponseEntity.ok(submissionService.getHiddenSubmission(submissionDto, principal));
    }

//    @GetMapping(value = "/submission", params = {"username"})
//    @PreAuthorize(allAuth)
//    public ResponseEntity<List<CreateHiddenSubmissionDto>> getSubmissionByUser(@Valid @RequestParam String username, Principal principal) {
////        try {
////            checkValidUsername(principal, username);
//        return ResponseEntity.ok(submissionService.getSubmissionByUsername(username));
////        } catch (Exception e) {
////            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Not Found");
////        }
//    }

    @GetMapping(value = "/submission")
    @PreAuthorize(allAuth)
    public ResponseEntity<List<GetSubmissionDto>> getSubmissions(@Valid @RequestParam(value = "username", required = false) String username,
                                                                 @Valid @RequestParam(value = "contestProblemId", required = false) Long contestProblemId,
                                                                 @Valid @RequestParam(value = "status", required = false) String status,
                                                                 Principal principal) {
        return ResponseEntity.ok(submissionService.getSubmissionByCondition(principal, contestProblemId, username, status));
    }

    @GetMapping(value = "/sourcecode")
    @PreAuthorize(allAuth)
    public ResponseEntity<GetSubmissionDto> getSourceCode(@Valid @RequestParam(value = "submissionId") Long submissionId, Principal principal) {
        System.out.println("submissionId = " + submissionId);
        return ResponseEntity.ok(submissionService.getSubmissionWithSourceCode(principal, submissionId));
    }

}
