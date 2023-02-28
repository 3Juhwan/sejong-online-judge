package com.example.demo.controller;

import com.example.demo.dto.ProblemDto;
import com.example.demo.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping("/new-problem")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ProblemDto> addProblem(@Valid @RequestBody ProblemDto problemDto) {
        return ResponseEntity.ok(problemService.saveProblem(problemDto));
    }
}
