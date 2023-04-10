package com.example.demo.entity;

import com.example.demo.dto.user.CreateUserDto;
import com.example.demo.dto.user.UpdateUserDto;
import com.example.demo.entity.util.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "creator")
    private List<Problem> problemsList;

    @OneToMany(mappedBy = "user")
    private List<CourseUser> courseUser;

    @OneToMany(mappedBy = "creator")
    private List<Course> createdCourses;

    @OneToMany(mappedBy = "user")
    private List<Submission> submissionList;


    public static User toEntity(CreateUserDto userDto, String password) {
        return User.builder()
                .username(userDto.getUsername())
                .password(password)
                .email(userDto.getEmail())
                .authority(userDto.getAuth())
                .activated(true)
                .build();
    }

    public User updateEntity(UpdateUserDto userDto) {
        this.email = userDto.getEmail();
        return this;
    }
}