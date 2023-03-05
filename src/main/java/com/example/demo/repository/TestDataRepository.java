package com.example.demo.repository;

import com.example.demo.entity.TestData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestDataRepository extends JpaRepository<TestData, Long> {

    List<TestData> findByProblemId(Long problemId);
}
