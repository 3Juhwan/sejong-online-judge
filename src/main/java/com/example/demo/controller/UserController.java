package com.example.demo.controller;

import com.example.demo.dto.user.UpdateUserDto;
import com.example.demo.dto.user.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;

import static com.example.demo.util.AuthUtil.adminOnlyAuth;
import static com.example.demo.util.AuthUtil.allAuth;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('TA')")
    public ResponseEntity<?> myInfo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping(value = "/user", params = "username")
    @PreAuthorize(adminOnlyAuth)
    public ResponseEntity<UserDto> getUser(@Valid @RequestParam String username) {
        return ResponseEntity.ok(userService.getUserWithAuthority(username));
    }

    @GetMapping("/user/mypage")
    @PreAuthorize(allAuth)
    public ResponseEntity<UserDto> getMyInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthority());
    }

    @PutMapping("/user/mypage")
    @PreAuthorize(allAuth)
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UpdateUserDto userDto, Principal principle) {
        userService.updateUser(userDto, principle);
        return ResponseEntity.noContent().build();
    }
}