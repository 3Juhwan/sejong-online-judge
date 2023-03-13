package com.example.demo.repository;

import com.example.demo.entity.ContestProblem;
import com.example.demo.entity.SubmitStatus;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubmitStatusRepository extends JpaRepository<SubmitStatus, Long> {

    Optional<SubmitStatus> findByUser(User user);
//    Optional<SubmitStatus> findByRegisteredProblemAndAndUser(ContestProblem registeredProblem, User user);

}
