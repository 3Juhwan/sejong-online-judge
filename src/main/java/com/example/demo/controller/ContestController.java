package com.example.demo.controller;

import com.example.demo.dto.contest.CreateContestDto;
import com.example.demo.dto.contest.GetContestByCourseDto;
import com.example.demo.dto.contest.GetContestDetailDto;
import com.example.demo.dto.contest.UpdateContestSequenceDto;
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


    @PostMapping("/contest/new")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<CreateContestDto> createContest(@Valid @RequestBody CreateContestDto contestDto) {
        return ResponseEntity.ok(contestService.saveContest(contestDto));
    }

    @PutMapping("/contest/{contestId}")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<Objects> updateContest(@Valid @RequestBody CreateContestDto contestDto, @PathVariable("contestId") Long contestId) {
        contestService.updateContest(contestDto, contestId);
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

    @PostMapping("/contest/sequence")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<Objects> updateContestSequence(@RequestBody List<UpdateContestSequenceDto> contestDto) {
        contestDto.forEach(contest -> {
            Long contestId = contest.getContestId();
            contestService.updateContestSequence(contest, contestId);
        });
        return ResponseEntity.noContent().build();
    }

}
