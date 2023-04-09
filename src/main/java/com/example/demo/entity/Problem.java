package com.example.demo.entity;

import com.example.demo.dto.problem.UpdateProblemDto;
import com.example.demo.entity.util.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Problem extends BaseTime {

    @Id
    @Column(name = "problem_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String title;

    private String content;

    private Long timeLimit;

    private Long memoryLimit;

    private String dirPath;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "contest_id")
    private Contest contest;

    @OneToMany(mappedBy = "problem")
    private List<TestData> testDataList;

    @OneToMany(mappedBy = "problem")
    private List<ContestProblem> registeredProblemList;

    @OneToMany(mappedBy = "problem")
    private List<Submission> submissionList;


    public Problem updateEntity(UpdateProblemDto problemDto) {
        this.title = problemDto.getTitle();
        this.content = problemDto.getContent();
        this.timeLimit = problemDto.getTimeLimit();
        this.memoryLimit = problemDto.getMemoryLimit();
        return this;
    }
}
