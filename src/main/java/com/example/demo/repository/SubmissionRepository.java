package com.example.demo.repository;

import com.example.demo.entity.Problem;
import com.example.demo.entity.User;
import com.example.demo.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    Optional<List<Submission>> findByUser(User user);
    Optional<Submission> findByUserAndProblem(User user, Problem problem);
}
