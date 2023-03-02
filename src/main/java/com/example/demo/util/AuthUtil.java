package com.example.demo.util;

public class AuthUtil {

    public static final String studentExclusiveAuth = "hasAnyRole('TA', 'PROFESSOR', 'ADMIN')";
    public static final String adminOnlyAuth = "hasAnyRole('ADMIN')";
}
