package com.example.demo.repository;

import com.example.demo.entity.ContestProblem;
import com.example.demo.entity.SubmitStatus;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubmitStatusRepository extends JpaRepository<SubmitStatus, Long> {

    Optional<SubmitStatus> findByUserAndContestProblem(User user, ContestProblem contestProblem);

    @Query("select ss from SubmitStatus ss where ss.user.username = :username and ss.contestProblem.id = :contestProblemId")
    Optional<SubmitStatus> findByUserAndContestProblem(@Param("username") String username, @Param("contestProblemId") Long contestProblemId);

}
