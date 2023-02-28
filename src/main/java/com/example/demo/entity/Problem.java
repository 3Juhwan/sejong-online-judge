package com.example.demo.entity;

import com.example.demo.dto.ProblemDto;
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

    @OneToMany(mappedBy = "problem")
    private List<TestData> testDataList;

    public Problem updateEntity(ProblemDto problemDto) {
        this.title = problemDto.getTitle();
        this.content = problemDto.getContent();
        this.timeLimit = problemDto.getTimeLimit();
        this.memoryLimit = problemDto.getMemoryLimit();
        return this;
    }
}
