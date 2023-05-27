package com.example.demo.controller;

import com.example.demo.dto.grade.GetGradedScoreBoardListResponseDto;
import com.example.demo.dto.grade.GetGradedScoreBoardResponseDto;
import com.example.demo.dto.grade.PostGradedScoreBoardRequestDto;
import com.example.demo.dto.submission.PostGradedScoreRequestDto;
import com.example.demo.service.GradeService;
import com.example.demo.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

import static com.example.demo.util.AuthUtil.studentExclusiveAuth;

@RestController
@RequestMapping("/api/v1/grade")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;
    private final SubmissionService submissionService;


    @GetMapping(value = "/list")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<List<GetGradedScoreBoardListResponseDto>> getGradedScoreBoardList(Principal principal) {
        return ResponseEntity.ok(gradeService.getGradedScoreBoardList(principal));
    }

    @GetMapping(value = "")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<List<GetGradedScoreBoardResponseDto>> getGradedScoreBoard(@Valid @RequestParam(value = "gradeId") Long gradeId) {
        return ResponseEntity.ok(submissionService.getGradedScoreBoard(gradeId));
    }

    @PostMapping(value = "")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<List<GetGradedScoreBoardResponseDto>> saveGradedScoreBoard(@Valid @RequestBody PostGradedScoreBoardRequestDto requestDto, Principal principal) {
        return ResponseEntity.ok(gradeService.saveGradedScoreBoard(requestDto, principal));
    }

    @PutMapping(value = "")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<Objects> saveGradedScore(@Valid @RequestBody List<PostGradedScoreRequestDto> requestDtoList) {
        submissionService.saveGradedScore(requestDtoList);
        return ResponseEntity.noContent().build();
    }
}
