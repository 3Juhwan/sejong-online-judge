package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class CourseUserId implements Serializable {

    private Long user;
    private Long course;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseUserId that = (CourseUserId) o;
        return Objects.equals(user, that.user) && Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, course);
    }
}
