package com.example.demo.service;

import com.example.demo.dto.testdata.GetTestDataDto;
import com.example.demo.dto.testdata.SaveTestDataDto;
import com.example.demo.entity.Problem;
import com.example.demo.entity.TestData;
import com.example.demo.repository.ProblemRepository;
import com.example.demo.repository.TestDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TestDataService {

    private final ProblemRepository problemRepository;
    private final TestDataRepository testDataRepository;

    @Transactional
    public SaveTestDataDto saveTestData(SaveTestDataDto testDataDto) {
        Problem problem = problemRepository.findById(testDataDto.getProblemId()).orElse(null);

        TestData testData = TestData.builder()
                .problem(problem)
                .input(testDataDto.getInput())
                .output(testDataDto.getOutput())
                .hidden(testDataDto.getHidden())
                .build();

        return SaveTestDataDto.from(testDataRepository.save(testData));
    }

    @Transactional
    public SaveTestDataDto updateTestData(SaveTestDataDto testDataDto) {
        TestData updatedTestData = testDataRepository.findById(testDataDto.getTestDataId()).orElse(null)
                .updateEntity(testDataDto);
        return SaveTestDataDto.from(testDataRepository.save(updatedTestData));
    }

    @Transactional
    public void deleteTestData(Long testDataId) {
        testDataRepository.deleteById(testDataId);
    }

    public GetTestDataDto getTestData(Long testDataId) {
        return GetTestDataDto.from(testDataRepository.findById(testDataId).orElse(null));
    }

    public List<GetTestDataDto> getTestDataByProblem(Long problemId) {
        List<TestData> testData = testDataRepository.findByProblemId(problemId);
        return testData.stream().map(GetTestDataDto::from).toList();
    }
}
