package com.example.demo.controller;

import com.example.demo.dto.problem.GetProblemDto;
import com.example.demo.dto.problem.CreateProblemDto;
import com.example.demo.dto.problem.UpdateProblemDto;
import com.example.demo.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

import static com.example.demo.util.AuthUtil.allAuth;
import static com.example.demo.util.AuthUtil.studentExclusiveAuth;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping("/problem/new")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<Objects> createProblem(@Valid @RequestBody CreateProblemDto problemDto) {
        problemService.saveProblem(problemDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/problem/{id}")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<CreateProblemDto> updateProblem(@Valid @RequestBody UpdateProblemDto problemDto) {
        return ResponseEntity.ok(problemService.updateProblem(problemDto));
    }

    @DeleteMapping("/problem/{id}")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<Objects> deleteProblem(@PathVariable("id") Long problemId) {
        problemService.deleteProblem(problemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/problem", params = "problemId")
    @PreAuthorize(allAuth)
    public ResponseEntity<GetProblemDto> getOneProblem(@Valid @RequestParam Long problemId) {
        return ResponseEntity.ok(problemService.getOneProblem(problemId));
    }
}
