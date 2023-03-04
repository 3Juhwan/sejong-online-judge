package com.example.demo.entity;

import com.example.demo.dto.testdata.TestDataDto;
import com.example.demo.entity.util.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestData extends BaseTime {

    @Id
    @Column(name = "test_data_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    private String input;

    private String output;

    public TestData updateEntity(TestDataDto testDataDto) {
        this.input = testDataDto.getInput();
        this.output = testDataDto.getOutput();
        return this;
    }

}
