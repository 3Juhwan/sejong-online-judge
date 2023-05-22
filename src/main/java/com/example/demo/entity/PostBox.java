package com.example.demo.entity;

import com.example.demo.entity.util.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class PostBox extends BaseTime {

    @Id
    @Column(name = "post_box_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id")
    private Contest contest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_problem_id")
    private ContestProblem contestProblem;

    @OneToMany(mappedBy = "postBox", fetch = FetchType.LAZY)
    private List<Post> postList;

    private Boolean finished;

    @PrePersist
    public void init() {
        this.finished = Boolean.FALSE;
    }

}
