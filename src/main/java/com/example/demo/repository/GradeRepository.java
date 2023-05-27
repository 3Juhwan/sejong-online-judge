package com.example.demo.repository;


import com.example.demo.entity.Grade;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {


    Optional<List<Grade>> findAllByCreator(User user);

}
