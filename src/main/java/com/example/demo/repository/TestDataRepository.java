package com.example.demo.repository;

import com.example.demo.entity.TestData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestDataRepository extends JpaRepository<TestData, Long> {

    List<TestData> findByProblemId(Long problemId);

    Optional<List<TestData>> findByProblemIdAndHidden(Long problemId, Boolean hidden);
}
