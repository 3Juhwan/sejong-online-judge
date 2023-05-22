package com.example.demo.controller;

import com.example.demo.dto.postBox.CreatePostRequestDto;
import com.example.demo.dto.postBox.PostResponseDto;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static com.example.demo.util.AuthUtil.allAuth;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/new")
    @PreAuthorize(allAuth)
    public ResponseEntity<PostResponseDto> createPost(@RequestBody CreatePostRequestDto requestDto, Principal principal) {
        return ResponseEntity.ok(postService.savePost(requestDto, principal));
    }

    // TODO 수정을 요청한 Post가 본인 소유인지 확인
    @PutMapping
    @PreAuthorize(allAuth)
    public ResponseEntity<PostResponseDto> updatePost(@RequestBody CreatePostRequestDto requestDto, Principal principal) {
        return ResponseEntity.ok(postService.savePost(requestDto, principal));
    }

    @GetMapping(params = "postBoxId")
    @PreAuthorize(allAuth)
    public ResponseEntity<List<PostResponseDto>> getPostByPostBox(@Valid @RequestParam Long postBoxId, Principal principal) {
        return ResponseEntity.ok(postService.getPostByPostBox(postBoxId, principal));
    }
}
