package com.example.demo.entity;

import com.example.demo.entity.util.BaseTime;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Table(name = "user")
@Entity
@Getter
@Setter
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
    @Pattern(regexp = "^[a-z0-9]{5,10}$",
            message = "닉네임은 특수문자를 제외한 5~10자리여야 합니다.")
    @Size(min = 5, max = 10)
    private String username;

    @NotNull
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    @Size(min = 8, max = 16)
    private String password;

    @NotNull
    @Email(message = "이메일 형식을 확인해주세요.")
    @Size(max = 30)
    private String email;

    @NotNull
    private String authority;

    private boolean activated;

}