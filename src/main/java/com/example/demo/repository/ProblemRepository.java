package com.example.demo.repository;

import com.example.demo.dto.problem.GetProblemDto;
import com.example.demo.entity.Problem;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

    @Query("select p.title as title, p.id as problemId, p.dirPath as dirPath from Problem p where p.creator = :user")
    Optional<List<GetProblemDto>> findAllByCreator(@Param("user") User user);

}
