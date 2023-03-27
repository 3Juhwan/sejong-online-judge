package com.example.demo.controller;

import com.example.demo.dto.language.GetLanguageListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.util.AuthUtil.allAuth;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LanguageController {

    @GetMapping(value = "/language-list")
    @PreAuthorize(allAuth)
    public ResponseEntity<GetLanguageListDto> getLanguageList() {
        return ResponseEntity.ok(GetLanguageListDto.builder().build());
    }

}
