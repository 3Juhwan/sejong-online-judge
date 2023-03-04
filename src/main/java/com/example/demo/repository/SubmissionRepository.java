package com.example.demo.repository;

import com.example.demo.entity.Problem;
import com.example.demo.entity.User;
import com.example.demo.entity.submission.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    Optional<Submission> findByUserAndProblem(User user, Problem problem);
}