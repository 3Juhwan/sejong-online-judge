package com.example.demo.dto.courseuser;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SaveCourseUserResponseDto {
    private List<String> savedUser = new ArrayList<>();
    private List<String> deletedUser = new ArrayList<>();
    private List<String> unregisteredUser = new ArrayList<>();

    public void addSavedUser(String username) {
        savedUser.add(username);
    }

    public void addDeletedUser(String username) {
        deletedUser.add(username);
    }

    public void addUnregisteredUser(String username) {
        unregisteredUser.add(username);
    }
}
