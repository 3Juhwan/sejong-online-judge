package com.example.demo.service;


import com.example.demo.dto.user.CreateUserDto;
import com.example.demo.dto.user.UpdateUserDto;
import com.example.demo.dto.user.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public CreateUserDto signup(CreateUserDto userDto) {
        if (userRepository.findOneWithAuthorityByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        User user = User.toEntity(userDto, passwordEncoder.encode(userDto.getPassword()));
        return CreateUserDto.from(userRepository.save(user));
    }

    public UserDto getUserWithAuthority(String username) {
        return UserDto.from(userRepository.findOneWithAuthorityByUsername(username).orElse(null));
    }

    public UserDto getMyUserWithAuthority() {
        return UserDto.from(SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthorityByUsername).orElse(null));
    }

    @Transactional
    public void updateUser(UpdateUserDto userDto, Principal principal) {
        User user = userRepository.findOneWithAuthorityByUsername(principal.getName()).orElse(null);
        userRepository.save(user.updateEntity(userDto));
    }


    public Boolean checkIfStudent(String username) {
        User user = userRepository.findOneWithAuthorityByUsername(username).orElse(null);
        if(user == null || user.getAuthority().equals("ROLE_STUDENT")) {
            return Boolean.TRUE;
        }
        else {
            return Boolean.FALSE;
        }
    }

}