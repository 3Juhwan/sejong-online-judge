package com.example.demo.entity;

import com.example.demo.dto.course.UpdateCourseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Language {

    private Boolean c;
    private Boolean cpp;
    private Boolean java;
    private Boolean python;

    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "language")
    private Course course;


    public Language(String setLanguage) {
        this.c = false;
        this.cpp = false;
        this.java = false;
        this.python = false;

        if (setLanguage == null) {
            return;
        }

        switch (setLanguage) {
            case "c" -> this.c = true;
            case "cpp" -> this.cpp = true;
            case "java" -> this.java = true;
            case "python" -> this.python = true;
        }
    }

    public void updateEntity(UpdateCourseDto.Language language) {
        this.c = language.getC();
        this.cpp = language.getCpp();
        this.java = language.getJava();
        this.python = language.getPython();
    }
}
