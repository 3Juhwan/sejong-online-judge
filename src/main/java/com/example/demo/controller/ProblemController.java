package com.example.demo.controller;

import com.example.demo.dto.ProblemDto;
import com.example.demo.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Objects;

import static com.example.demo.util.AuthUtil.studentExclusiveAuth;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping("/new-problem")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<ProblemDto> addProblem(@Valid @RequestBody ProblemDto problemDto) {
        return ResponseEntity.ok(problemService.saveProblem(problemDto));
    }

    @PostMapping("/problem/{id}/update")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<ProblemDto> updateProblem(@Valid @RequestBody ProblemDto problemDto) {
        return ResponseEntity.ok(problemService.updateProblem(problemDto));
    }

    @GetMapping("/problem/{id}/delete")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<Objects> deleteProblem(@PathVariable("id") Long problemId) {
        problemService.deleteProblem(problemId);
        return ResponseEntity.noContent().build();
    }

}
