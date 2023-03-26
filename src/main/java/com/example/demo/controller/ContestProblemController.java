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
     * 콘테스트 문제 목록 조회
     *
     * @param contestId 콘테스트 ID
     * @param principal 인증 정보
     * @return 콘테스트 문제 목록
     */
    @GetMapping(value = "/contest/{contestId}/problems")
    @PreAuthorize(allAuth)
    public ResponseEntity<List<GetContestProblemByContestDto>> getContestProblems(@PathVariable("contestId") Long contestId, Principal principal) {
        return ResponseEntity.ok(contestProblemService.getContestProblems(contestId, principal));
    }

}
