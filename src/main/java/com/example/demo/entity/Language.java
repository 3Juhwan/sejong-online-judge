package com.example.demo.entity;

import com.example.demo.dto.language.GetLanguageListDto;
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

    public Language(GetLanguageListDto language) {
        updateEntity(language);
    }

    public void updateEntity(GetLanguageListDto language) {
        this.c = language.getC();
        this.cpp = language.getCpp();
        this.java = language.getJava();
        this.python = language.getPython();
    }
}
