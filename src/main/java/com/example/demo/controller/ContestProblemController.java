package com.example.demo.controller;

import com.example.demo.dto.contestProblem.GetContestProblemByContestDto;
import com.example.demo.dto.contestProblem.SaveContestProblemDto;
import com.example.demo.dto.contestProblem.SaveContestProblemResponseDto;
import com.example.demo.service.ContestProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.demo.util.AuthUtil.allAuth;
import static com.example.demo.util.AuthUtil.studentExclusiveAuth;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ContestProblemController {

    private final ContestProblemService contestProblemService;

    @PostMapping("/contest-problem/new")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<SaveContestProblemResponseDto> saveContestProblem(@Valid @RequestBody SaveContestProblemDto contestProblemToContestDto) {
        return ResponseEntity.ok(contestProblemService.saveContestProblem(contestProblemToContestDto));
    }

    /**
     * deprecated
     *
     * @param contestId 콘테스트 ID
     * @return 콘테스트 문제 목록
     */
    @GetMapping(value = "/contest-problem", params = "contestId")
    @PreAuthorize(allAuth)
    public ResponseEntity<List<GetContestProblemByContestDto>> getContestProblemList(@RequestParam Long contestId) {
        return ResponseEntity.ok(contestProblemService.getContestProblemList(contestId));
    }

}
