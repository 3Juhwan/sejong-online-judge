package com.example.demo.entity;

import com.example.demo.entity.util.BaseTime;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Course extends BaseTime {

    @Id @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Long linkedCourseId;

    @OneToMany(mappedBy = "course")
    private List<Contest> contests;

    @OneToMany(mappedBy = "user")
    private List<User> registeredUsers;

}
