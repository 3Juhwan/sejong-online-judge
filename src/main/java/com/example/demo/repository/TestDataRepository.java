package com.example.demo.repository;

import com.example.demo.dto.testdata.TestDataDto;
import com.example.demo.entity.Problem;
import com.example.demo.entity.TestData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TestDataRepository extends JpaRepository<TestData, Long> {

    List<TestData> findAllByProblemId(Long problemId);

    Optional<List<TestData>> findByProblemIdAndHidden(Long problemId, Boolean hidden);

    @Query("select td.id as dataSequence, td.input as inputData, td.output as outputData from TestData td where td.problem = :problem")
    Optional<List<TestDataDto>> findAllTestDataIdByProblem(@Param("problem") Problem problem);
}
