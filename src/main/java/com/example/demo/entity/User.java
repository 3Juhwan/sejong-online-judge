package com.example.demo.entity;

import com.example.demo.entity.util.BaseTime;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Table(name = "user")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTime {

    @Id
    @Column(name = "user_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, updatable = false)
    @Pattern(regexp = "^[a-z0-9]{5,10}$")
    @Size(min = 5, max = 10)
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Email
    @Size(max = 30)
    private String email;

    @NotNull
    private String authority;

    @NotNull
    private boolean activated;

    @OneToMany(mappedBy = "user")
    private List<CourseUser> courseUser;

    @OneToMany(mappedBy = "creator")
    private List<Course> createdCourses;

}