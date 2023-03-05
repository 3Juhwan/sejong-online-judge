package com.example.demo.entity;

import com.example.demo.entity.util.BaseTime;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Contest extends BaseTime {

    @Id
    @Column(name = "contest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "contest")
    private List<Problem> contestProblems;
}
