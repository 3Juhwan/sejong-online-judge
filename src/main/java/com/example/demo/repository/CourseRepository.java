package com.example.demo.repository;

import com.example.demo.dto.course.GetCourseDto;
import com.example.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c.title as title, c.id as courseId, c.semester as semester, c.language as language from Course c where c.id = :courseId")
    Optional<GetCourseDto> findCourseByCourseId(@Param("courseId") Long courseId);
}
