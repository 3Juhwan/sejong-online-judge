package com.example.demo.controller;

import com.example.demo.dto.postBox.CreatePostBoxRequestDto;
import com.example.demo.dto.postBox.PostBoxResponseDto;
import com.example.demo.service.PostBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

import static com.example.demo.util.AuthUtil.allAuth;

@RestController
@RequestMapping("/api/v1/postbox")
@RequiredArgsConstructor
public class PostBoxController {

    private final PostBoxService postBoxService;

    @PostMapping("/new")
    @PreAuthorize(allAuth)
    public ResponseEntity<PostBoxResponseDto> createPostBox(@RequestBody CreatePostBoxRequestDto requestDto, Principal principal) {
        return ResponseEntity.ok(postBoxService.createPostbox(requestDto, principal));
    }

    @DeleteMapping(params = {"postBoxId"})
    public ResponseEntity<Objects> deletePostBox(@Valid @RequestParam Long postBoxId, Principal principal) {
        postBoxService.deletePostBox(postBoxId, principal);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(params = {"courseId"})
    @PreAuthorize(allAuth)
    public ResponseEntity<List<PostBoxResponseDto>> getPostBox(@Valid @RequestParam Long courseId, Principal principal) {
        return ResponseEntity.ok(postBoxService.getPostBox(courseId, principal));
    }

}
