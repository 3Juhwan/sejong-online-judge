package com.example.demo.util;

public class AuthUtil {

    public static final String allAuth = "hasAnyRole('STUDENT', 'TA', 'PROFESSOR', 'ADMIN')";
    public static final String studentExclusiveAuth = "hasAnyRole('TA', 'PROFESSOR', 'ADMIN')";
    public static final String professorAuth = "hasAnyRole('PROFESSOR', 'ADMIN')";
    public static final String adminOnlyAuth = "hasAnyRole('ADMIN')";
}
