package com.example.demo.entity;

import com.example.demo.dto.submitstatus.UpdateSubmitStatusDto;
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
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "contest_problem_id")
    private ContestProblem contestProblem;

    @OneToMany(mappedBy = "submitStatus")
    private List<Submission> submissionList;


    public SubmitStatus updateEntity(UpdateSubmitStatusDto submitStatusDto) {
        this.submitCnt++;
        this.highScore = Math.max(this.highScore, submitStatusDto.getScore());
        return this;
    }

    public static SubmitStatus getInitEntity(User user) {
        return SubmitStatus.builder()
                .user(user)
                .highScore(0L)
                .submitCnt(0L)
                .build();
    }
}
