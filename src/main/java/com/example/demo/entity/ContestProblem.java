package com.example.demo.entity;

import com.example.demo.dto.contestProblem.SaveContestProblemDto;
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

    private Long sequence;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "contest_id")
    private Contest contest;

    @OneToMany(mappedBy = "contestProblem")
    private List<SubmitStatus> submitStatuses;

    @OneToMany(mappedBy = "contestProblem", cascade = CascadeType.ALL)
    private List<PostBox> postList;

    public static ContestProblem toEntity(SaveContestProblemDto registerProblemDto, Problem problem, Contest contest) {
        return ContestProblem.builder()
                .title(registerProblemDto.getTitle())
                .sequence(registerProblemDto.getSequence())
                .problem(problem)
                .contest(contest)
                .build();
    }

    public ContestProblem update(String title, Long sequence, Problem problem) {
        this.title = title;
        this.sequence = sequence;
        this.problem = problem;
        return this;
    }
}
