package com.example.demo.controller;

import com.example.demo.dto.contestProblem.SaveContestProblemDto;
import com.example.demo.dto.contestProblem.SaveContestProblemResponseDto;
import com.example.demo.service.ContestProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

import static com.example.demo.util.AuthUtil.studentExclusiveAuth;

@RestController
@RequestMapping("/api/v1/contest-problem")
@RequiredArgsConstructor
public class ContestProblemController {

    private final ContestProblemService contestProblemService;

    @PostMapping("/new")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<SaveContestProblemResponseDto> saveContestProblem(@Valid @RequestBody SaveContestProblemDto contestProblemToContestDto) {
        return ResponseEntity.ok(contestProblemService.saveContestProblem(contestProblemToContestDto));
    }

    @PutMapping("/{contestProblemId}")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<Objects> updateContestProblem(@Valid @RequestBody SaveContestProblemDto requestDto, @PathVariable("contestProblemId") Long contestProblemId) {
        contestProblemService.updateContestProblem(requestDto, contestProblemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{contestProblemId}")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<Objects> deleteContestProblem(@PathVariable("contestProblemId") Long contestProblemId) {
        contestProblemService.deleteContestProblem(contestProblemId);
        return ResponseEntity.noContent().build();
    }

}
