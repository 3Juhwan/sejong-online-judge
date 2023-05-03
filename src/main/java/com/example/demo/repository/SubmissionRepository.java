package com.example.demo.repository;

import com.example.demo.entity.ContestProblem;
import com.example.demo.entity.Submission;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    @Query("SELECT s FROM Submission s WHERE (:user IS NULL OR s.user = :user) AND (:contestProblem IS NULL OR s.contestProblem = :contestProblem) AND (:status IS NULL OR s.status = :status)")
    Optional<Page<Submission>> findAllByConditions(@Param("user") User user, @Param("contestProblem") ContestProblem contestProblem, @Param("status") String status, Pageable pageable);
}
