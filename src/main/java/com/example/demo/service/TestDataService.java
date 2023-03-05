package com.example.demo.service;

import com.example.demo.dto.testdata.TestDataDto;
import com.example.demo.entity.Problem;
import com.example.demo.entity.TestData;
import com.example.demo.repository.ProblemRepository;
import com.example.demo.repository.TestDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TestDataService {

    private final ProblemRepository problemRepository;
    private final TestDataRepository testDataRepository;

    @Transactional
    public TestDataDto saveTestData(TestDataDto testDataDto) {
        Problem problem = problemRepository.findById(testDataDto.getProblemId()).get();

        TestData testData = TestData.builder()
                .problem(problem)
                .input(testDataDto.getInput())
                .output(testDataDto.getOutput())
                .build();

        return TestDataDto.from(testDataRepository.save(testData));
    }

    @Transactional
    public TestDataDto updateTestData(TestDataDto testDataDto) {
        TestData updatedTestData = testDataRepository.findById(testDataDto.getId())
                .get()
                .updateEntity(testDataDto);

        return TestDataDto.from(testDataRepository.save(updatedTestData));
    }

    public void deleteTestData(Long testDataId) {
        testDataRepository.deleteById(testDataId);
    }

    public TestDataDto getSingleTestData(Long testDataId) {
        return TestDataDto.from(testDataRepository.findById(testDataId).get());
    }

    public List<TestDataDto> getProblemTestData(Long problemId) {
        List<TestData> testData = testDataRepository.findByProblemId(problemId);
        List<TestDataDto> testDataDtoList = new ArrayList<>();
        for (TestData data : testData) {
            testDataDtoList.add(TestDataDto.from(data));
        }
        return testDataDtoList;
    }
}
