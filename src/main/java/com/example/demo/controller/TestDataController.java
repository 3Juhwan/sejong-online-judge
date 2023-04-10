package com.example.demo.controller;

import com.example.demo.dto.testdata.GetTestDataDto;
import com.example.demo.dto.testdata.SaveTestDataDto;
import com.example.demo.service.TestDataService;
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
public class TestDataController {

    private final TestDataService testDataService;


    @PostMapping("/testdata/new")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<SaveTestDataDto> saveTestData(@Valid @RequestBody SaveTestDataDto testDataDto) {
        return ResponseEntity.ok(testDataService.saveTestData(testDataDto));
    }

    @PutMapping("/testdata")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<SaveTestDataDto> updateTestData(@Valid @RequestBody SaveTestDataDto testDataDto) {
        return ResponseEntity.ok(testDataService.updateTestData(testDataDto));
    }

    @DeleteMapping("/testdata/{id}")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<Objects> deleteTestData(@PathVariable("id") Long testDataId) {
        testDataService.deleteTestData(testDataId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/testdata", params = {"testDataId"})
    @PreAuthorize(allAuth)
    public ResponseEntity<GetTestDataDto> getTestData(@Valid @RequestParam Long testDataId) {
        return ResponseEntity.ok(testDataService.getTestData(testDataId));
    }

    @GetMapping(value = "/testdata", params = {"problemId"})
    public ResponseEntity<List<GetTestDataDto>> getTestDataByProblem(@Valid @RequestParam Long problemId) {
        return ResponseEntity.ok(testDataService.getTestDataByProblem(problemId));
    }

    @GetMapping(value = "/testdata/sample", params = {"problemId"})
    public ResponseEntity<List<GetTestDataDto>> getSampleTestDataByProblem(@Valid @RequestParam Long problemId) {
        return ResponseEntity.ok(testDataService.getSampleTestDataByProblem(problemId));
    }

}
