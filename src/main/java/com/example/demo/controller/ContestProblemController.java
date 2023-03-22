package com.example.demo.controller;

import com.example.demo.dto.contestProblem.GetContestProblemByContestDto;
import com.example.demo.service.ContestProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static com.example.demo.util.AuthUtil.allAuth;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ContestProblemController {

    private final ContestProblemService contestProblemService;

    /**
     * 주어진 정보로 콘테스트 문제 목록 가져오기
     *
     * @param contestId
     * @param principal
     * @return 콘테스트 문제 목록
     */
    @GetMapping(value = "/contest/{contestId}/problems")
    @PreAuthorize(allAuth)
    public ResponseEntity<List<GetContestProblemByContestDto>> getContestProblems(@PathVariable("contestId") Long contestId, Principal principal) {
        return ResponseEntity.ok(contestProblemService.getContestProblems(contestId, principal));
    }

}
