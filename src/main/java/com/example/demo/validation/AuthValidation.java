package com.example.demo.validation;

import java.security.Principal;

public class AuthValidation {

    public static void checkValidUsername(Principal principal, String username) throws Exception {
        String authUsername = principal.getName();
        if (!authUsername.equals(username)) {
            throw new Exception("사용자와 입력한 이름이 다릅니다. ");
        }
    }
}
