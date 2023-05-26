package com.example.demo.controller;

import com.example.demo.dto.submission.*;
import com.example.demo.service.SubmissionService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.example.demo.util.AuthUtil.allAuth;
import static com.example.demo.util.AuthUtil.studentExclusiveAuth;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;
    private final UserService userService;

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
    public ResponseEntity<Page<GetSubmissionDto>> getSubmissionListByContestProblem(@Valid @RequestParam(value = "username", required = false) String username,
                                                                                    @Valid @RequestParam(value = "contestId", required = false) Long contestId,
                                                                                    @Valid @RequestParam(value = "contestProblemId", required = false) Long contestProblemId,
                                                                                    @Valid @RequestParam(value = "status", required = false) String status,
                                                                                    @PageableDefault(page = 0, size = 10, sort = "submitTime", direction = Sort.Direction.DESC) Pageable pageable,
                                                                                    Principal principal) {
        if (contestId == null || userService.checkIfStudent(principal.getName())) {
            return ResponseEntity.ok(submissionService.getSubmissionListByContestProblem(principal, contestProblemId, username, status, pageable));
        }
        return ResponseEntity.ok(submissionService.getSubmissionListByContest(principal, contestId, username, status, pageable));
    }


    @GetMapping(value = "/sourcecode")
    @PreAuthorize(allAuth)
    public ResponseEntity<GetSubmissionDto> getSourceCode(@Valid @RequestParam(value = "submissionId") Long submissionId, Principal principal) {
        return ResponseEntity.ok(submissionService.getSubmissionWithSourceCode(principal, submissionId));
    }

    @GetMapping(value = "/submission/total")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<List<GetTotalSubmissionDto>> getTotalStatus(@Valid @RequestParam(value = "contestId") Long contestId, @RequestParam(value = "endingTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endingTime) {
        return ResponseEntity.ok(submissionService.getTotalStatus(contestId, endingTime));
    }

    @GetMapping(value = "/submission/graded")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<List<GetGradedScoreBoardResponseDto>> getGradedScoreBoard(@Valid @RequestParam(value = "contestId") Long contestId, @RequestParam(value = "endingTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endingTime) {
        return ResponseEntity.ok(submissionService.getGradedScoreBoard(contestId, endingTime));
    }

    @PostMapping(value = "/submission/graded")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<Objects> saveGradedScore(@Valid @RequestBody List<PostGradedScoreRequestDto> requestDtoList) {
        submissionService.saveGradedScore(requestDtoList);
        return ResponseEntity.noContent().build();
    }

//    // TODO: 조교/교수/관리자 권한 확인
//    @GetMapping(value = "/submission", params = "contestProblemId")
//    public ResponseEntity<Objects> getSubmissionOfAllStudent(@Valid @RequestParam(value = "contestProblemId") Long contestProblemId) {
//        return ResponseEntity.ok(submissionService);
//    }

}
