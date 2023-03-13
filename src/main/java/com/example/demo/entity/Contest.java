package com.example.demo.entity;

import com.example.demo.entity.util.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contest extends BaseTime {

    @Id
    @Column(name = "contest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "contest")
    private List<ContestProblem> registeredProblemList;

    private LocalDateTime startingTime;

    private LocalDateTime endingTime;

//    @OneToMany(mappedBy = "contest")
//    private List<SubmitStatus> submitStatusList;
}
