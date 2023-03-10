package com.example.demo.entity;

import com.example.demo.entity.util.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContestProblem extends BaseTime {

    @Id
    @Column(name = "contest_problem_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contest_id")
    private Contest contest;

    @OneToMany(mappedBy = "contestProblem")
    private List<SubmitStatus> submitStatuses;

    public static ContestProblem toEntity(Problem problem, Contest contest) {
        return ContestProblem.builder()
                .problem(problem)
                .contest(contest)
                .build();
    }
}
