package com.example.demo.service;

import com.example.demo.dto.postBox.CreatePostRequestDto;
import com.example.demo.dto.postBox.PostResponseDto;
import com.example.demo.entity.Post;
import com.example.demo.entity.PostBox;
import com.example.demo.entity.User;
import com.example.demo.repository.ContestProblemRepository;
import com.example.demo.repository.PostBoxRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostBoxRepository postBoxRepository;
    private final ContestProblemRepository contestProblemRepository;
    private final UserRepository userRepository;

    public PostResponseDto savePost(CreatePostRequestDto requestDto, Principal principal) {
        Optional<User> userOptional = userRepository.findByUsername(principal.getName());
        if (userOptional.isEmpty()) {
            System.out.println("user가 없습니다. ");
        }
        User user = userOptional.get();
        Optional<PostBox> postBoxOptional = postBoxRepository.findById(requestDto.getPostBoxId());
        if (postBoxOptional.isEmpty()) {
            System.out.println("postbox가 없습니다. ");
        }
        PostBox postBox = postBoxOptional.get();
        Post post = CreatePostRequestDto.toEntity(requestDto, user, postBox);
        return PostResponseDto.from(post);
    }

    public List<PostResponseDto> getPostByPostBox(Long postBoxId, Principal principal) {
        Optional<PostBox> postBoxOptional = postBoxRepository.findById(postBoxId);
        if (postBoxOptional.isEmpty()) {
            System.out.println("PostBox가 없습니다. ");
        }
        PostBox postBox = postBoxOptional.get();
        Optional<User> userOptional = userRepository.findByUsername(principal.getName());
        if (userOptional.isEmpty()) {
            System.out.println("User가 없습니다. ");
        }
        User user = userOptional.get();
        return postRepository.findAllByPostBoxAndAuthor(postBox, user).orElse(null).stream()
                .map(p -> PostResponseDto.from(p))
                .toList();
    }

}
