package com.example.demo.entity;

import com.example.demo.dto.course.UpdateCourseDto;
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
public class Course extends BaseTime {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id")
    Language language;

    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String semester;

    private Long linkedCourseId;

    @OneToMany(mappedBy = "course")
    private List<Contest> contests;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseUser> courseUser;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "creator_id")
    private User creator;

    @Builder.Default
    private Long numberOfContest = 0L;

    public Course updateEntity(UpdateCourseDto courseDto) {
        this.title = courseDto.getTitle();
        this.semester = courseDto.getSemester();
        this.language.updateEntity(courseDto.getLanguage());
        return this;
    }

    public Course updateEntity(Long numberOfContest) {
        this.numberOfContest = numberOfContest;
        return this;
    }
}
