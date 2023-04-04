package com.example.demo.repository;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseUser;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseUserRepository extends JpaRepository<CourseUser, Long> {

    List<CourseUser> findCourseUsersByUser(User user);

    List<CourseUser> findAllCourseUsersByCourse(Course course);
}
