package com.example.demo.dto.course;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserToCourseDto {
    private Long courseId;
    private String users;
    private List<String> userList;

    public void splitUsers() {
        this.userList = List.of(this.users.split("\n"));
    }
}
