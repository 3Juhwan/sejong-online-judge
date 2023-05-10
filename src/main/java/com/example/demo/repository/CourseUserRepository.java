package com.example.demo.repository;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseUser;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseUserRepository extends JpaRepository<CourseUser, Long> {

    Optional<List<CourseUser>> findAllByCourse(Course course);

    List<CourseUser> findCourseUsersByUser(User user);

    List<CourseUser> findAllCourseUsersByCourse(Course course);

    CourseUser findByUserAndCourse(User user, Course course);
}
