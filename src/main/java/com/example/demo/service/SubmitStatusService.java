package com.example.demo.service;

import com.example.demo.dto.submitstatus.CreateSubmitStatusDto;
import com.example.demo.dto.submitstatus.UpdateSubmitStatusDto;
import com.example.demo.entity.SubmitStatus;
import com.example.demo.entity.User;
import com.example.demo.repository.SubmitStatusRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class SubmitStatusService {

    private final SubmitStatusRepository submitStatusRepository;
    private final UserRepository userRepository;

    public void saveSubmitStatus(CreateSubmitStatusDto submitStatusDto) {

    }

    public void updateSubmitStatus(UpdateSubmitStatusDto submitStatusDto, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).get();
        SubmitStatus submitStatus = submitStatusRepository.findByUser(user).get();
        SubmitStatus updatedSubmitStatus = submitStatus.updateEntity(submitStatusDto);
        submitStatusRepository.save(updatedSubmitStatus);
    }

}
