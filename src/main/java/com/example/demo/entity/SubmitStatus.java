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
public class SubmitStatus extends BaseTime {

    @Id
    @Column(name = "submit_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long submitCnt;

    private Long highScore;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "contest_problem_id")
    private ContestProblem contestProblem;

    @OneToMany(mappedBy = "submitStatus")
    private List<Submission> submissionList;

    public SubmitStatus(User user, ContestProblem contestProblem) {
        this.user = user;
        this.contestProblem = contestProblem;
        this.highScore = 0L;
        this.submitCnt = 0L;
    }

    public SubmitStatus updateEntity(Long score) {
        this.submitCnt++;
        this.highScore = Math.max(this.highScore, score);
        return this;
    }
}
