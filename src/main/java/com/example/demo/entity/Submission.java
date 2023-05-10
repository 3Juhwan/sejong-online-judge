package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Submission {

    @Id
    @Column(name = "submission_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // Submit Info
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @OneToOne(mappedBy = "submission")
    private SubmitStatus submitStatus;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "contest_problem_id")
    private ContestProblem contestProblem;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "problem_id")
    private Problem problem;


    @NotNull
    private String code;

    // TODO language 관련해서 수정할 거리가 많습니다.
//    @NotNull
    private String language;

    private Long length;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime submitTime;

    private Long score;

    @Column(length = 10000)
    private String result;


    // Submit Result
    private Long timeUsage;

    private Long memoryUsage;

    //    @Enumerated(EnumType.STRING)
    private Long status;

}

