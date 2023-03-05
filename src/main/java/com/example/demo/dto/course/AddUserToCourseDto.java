package com.example.demo.dto.course;

import lombok.Getter;

import java.util.List;

@Getter
public class AddUserToCourseDto {
    private Long courseId;
    private String users;
    private List<String> userList;

    public void splitUsers() {
        this.userList = List.of(this.users.split("\n"));
    }
}
