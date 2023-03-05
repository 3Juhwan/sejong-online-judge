package com.example.demo.controller;

import com.example.demo.dto.testdata.TestDataDto;
import com.example.demo.service.TestDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static com.example.demo.util.AuthUtil.studentExclusiveAuth;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TestDataController {

    private final TestDataService testDataService;

    /**
     * 테스트데이터 등록
     *
     * @param testDataDto
     * @return ResponseEntity<TestDataDto>
     */
    @PostMapping("/new-testdata")
    public ResponseEntity<TestDataDto> addTestData(@Valid @RequestBody TestDataDto testDataDto) {
        return ResponseEntity.ok(testDataService.saveTestData(testDataDto));
    }

    /**
     * 테스트데이터 수정
     *
     * @param testDataDto
     * @return ResponseEntity<TestDataDto>
     */
    @PostMapping("/update-testdata")
    public ResponseEntity<TestDataDto> updateTestData(@Valid @RequestBody TestDataDto testDataDto) {
        return ResponseEntity.ok(testDataService.updateTestData(testDataDto));
    }

    /**
     * 테스트데이터 삭제 후 리다이렉트
     * @param testDataId
     * @return ResponseEntity<Objects>
     */
    @GetMapping("/testdata/{id}/delete")
    @PreAuthorize(studentExclusiveAuth)
    public ResponseEntity<Objects> deleteTestData(@PathVariable("id") Long testDataId) {
        testDataService.deleteTestData(testDataId);
        return ResponseEntity.noContent().build();
    }

    /**
     * 주어진 쿼리에 따라 테스트데이터 조회
     *
     * @param testDataId
     * @return ResponseEntity<TestDataDto>
     */
    @GetMapping(value = "/testdata", params = {"testDataId"})
    public ResponseEntity<TestDataDto> getSingleTestData(@Valid @RequestParam Long testDataId) {
        return ResponseEntity.ok(testDataService.getSingleTestData(testDataId));
    }

    /**
     * 주어진 쿼리에 따라 문제에 등록된 테스트데이터 조회
     *
     * @param problemId
     * @return ResponseEntity<List<TestDataDto>>
     */
    @GetMapping(value = "/testdata", params = {"problemId"})
    public ResponseEntity<List<TestDataDto>> getProblemTestData(@Valid @RequestParam Long problemId) {
        return ResponseEntity.ok(testDataService.getProblemTestData(problemId));
    }
}
