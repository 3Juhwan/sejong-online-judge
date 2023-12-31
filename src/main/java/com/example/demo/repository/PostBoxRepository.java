package com.example.demo.repository;

import com.example.demo.entity.ContestProblem;
import com.example.demo.entity.Course;
import com.example.demo.entity.PostBox;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostBoxRepository extends JpaRepository<PostBox, Long> {

    Optional<List<PostBox>> findAllByCourseAndUser(Course course, User user);

    Optional<List<PostBox>> findByCourse(Course course);

    Optional<PostBox> findByContestProblemAndUser(ContestProblem contestProblem, User user);
}
