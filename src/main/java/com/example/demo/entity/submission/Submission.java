package com.example.demo.entity.submission;

import com.example.demo.entity.Problem;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.Language;
import com.example.demo.entity.enums.Status;
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "problem_id", updatable = false)
    private Problem problem;

    @NotNull
    private String code;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Language language;

    private Long length;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime submitTime;


    // Submit Result
    private Long timeUsage;

    private Long memoryUsage;

    @Enumerated(EnumType.STRING)
    private Status status;

}

