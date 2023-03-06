package com.example.demo.controller;

import com.example.demo.dto.FindCourseContestDto;
import com.example.demo.dto.contest.AddContestDto;
import com.example.demo.service.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static com.example.demo.util.AuthUtil.allAuth;
import static com.example.demo.util.AuthUtil.studentExclusiveAuth;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ContestController {

    private final ContestService contestService;

    @PostMapping("/new-contest")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<List<AddContestDto>> addContest(@Valid @RequestBody List<AddContestDto> contestDto) {
        return ResponseEntity.ok(contestService.saveContest(contestDto));
    }

    @GetMapping(value = "/contest", params = {"courseId"})
    @PreAuthorize(allAuth)
    public ResponseEntity<List<FindCourseContestDto>> findContestByCourse(@Valid @RequestParam Long courseId) {
        return ResponseEntity.ok(contestService.findContestByContest(courseId));
    }

    @GetMapping("/contest/{id}/delete")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<Objects> deleteContest(@PathVariable("id") Long contestId) {
        contestService.deleteContest(contestId);
        return ResponseEntity.noContent().build();
    }

}
