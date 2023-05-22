package com.example.demo.entity;

import com.example.demo.dto.contest.CreateContestDto;
import com.example.demo.dto.contest.UpdateContestSequenceDto;
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

    @OneToMany(mappedBy = "contest", cascade = CascadeType.ALL)
    private List<PostBox> postList;

    private Long sequence;

    private LocalDateTime startingTime;

    private LocalDateTime endingTime;


    public Contest setDateTime(String startingTime, String endingTime) {
        if (startingTime == null) {
            startingTime = "0000-01-01T00:00:00";
        }
        if (endingTime == null) {
            endingTime = "9999-12-31T23:59:59";
        }
        this.startingTime = LocalDateTime.parse(startingTime);
        this.endingTime = LocalDateTime.parse(endingTime);
        return this;
    }

    public Contest updateEntity(CreateContestDto contestDto) {
        this.title = contestDto.getTitle();
        return this.setDateTime(contestDto.getStartingTime(), contestDto.getEndingTime());
    }

    public Contest updateEntity(UpdateContestSequenceDto contestDto) {
        this.sequence = contestDto.getSequence();
        return this;
    }
}
