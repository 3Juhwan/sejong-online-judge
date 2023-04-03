package com.example.demo.repository;

import com.example.demo.dto.contest.GetContestByCourseDto;
import com.example.demo.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {

    @Query("select c.title as title, c.id as id, c.sequence as sequence from Contest c where c.course.id = :courseId")
    List<GetContestByCourseDto> findAllContestByCourse(@Param("courseId") Long courseId);
}
