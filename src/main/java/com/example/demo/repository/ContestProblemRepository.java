package com.example.demo.repository;

import com.example.demo.dto.contestProblem.GetContestProblemByContestDto;
import com.example.demo.entity.Contest;
import com.example.demo.entity.ContestProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContestProblemRepository extends JpaRepository<ContestProblem, Long> {

    @Query("select cp.sequence as sequence, cp.title as title, cp.problem.id as problemId, cp.id as contestProblemId from ContestProblem cp where cp.contest.id = :contestId")
    List<GetContestProblemByContestDto> findContestProblemListContest(@Param("contestId") Long contestId);

    Optional<List<ContestProblem>> findAllContestProblemByContestId(Long contestId);

    Optional<List<ContestProblem>> findAllByContest(Contest contest);

}

