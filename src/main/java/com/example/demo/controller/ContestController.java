package com.example.demo.controller;

import com.example.demo.dto.contest.CreateContestDto;
import com.example.demo.dto.contest.GetContestByCourseDto;
import com.example.demo.dto.contest.GetContestDetailDto;
import com.example.demo.dto.contestProblem.SaveContestProblemToContestDto;
import com.example.demo.service.ContestProblemService;
import com.example.demo.service.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

import static com.example.demo.util.AuthUtil.allAuth;
import static com.example.demo.util.AuthUtil.studentExclusiveAuth;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ContestController {

    private final ContestService contestService;
    private final ContestProblemService contestProblemService;


    @PostMapping("/contest/new")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<Object> createContest(@Valid @RequestBody List<CreateContestDto> contestDto) {
        contestService.saveContests(contestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/contests", params = {"courseId"})
    @PreAuthorize(allAuth)
    public ResponseEntity<List<GetContestByCourseDto>> getContests(@Valid @RequestParam Long courseId) {
        return ResponseEntity.ok(contestService.getContests(courseId));
    }

    @GetMapping(value = "/contest", params = {"contestId"})
    @PreAuthorize(allAuth)
    public ResponseEntity<GetContestDetailDto> getContestInfo(@Valid @RequestParam Long contestId, Principal principal) {
        return ResponseEntity.ok(contestService.getContestDetail(contestId, principal));
    }

    @DeleteMapping("/contest/{contestId}")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<Objects> deleteContest(@PathVariable("contestId") Long contestId) {
        contestService.deleteContest(contestId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/contest/{contestId}/problems")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<Objects> saveContestProblemToContest(@PathVariable("contestId") Long contestId, @Valid @RequestBody SaveContestProblemToContestDto contestProblemToContestDto) {
        contestProblemService.saveContestProblemToContest(contestId, contestProblemToContestDto);
        return ResponseEntity.noContent().build();
    }

}
