//package com.example.demo.controller;
//
//import com.example.demo.dto.RegisterContestProblemDto;
//import com.example.demo.dto.contestProblem.GetContestProblemDto;
//import com.example.demo.dto.problem.GetProblemDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class ContestProblemController {
//
//    @GetMapping(value = "/contest-problem", params = "contestId")
//    public ResponseEntity<List<GetContestProblemDto>> getContestProblems(@Valid @RequestParam Long contestId) {
//        return ResponseEntity.ok(problemService)
//
//    }
//
//}
