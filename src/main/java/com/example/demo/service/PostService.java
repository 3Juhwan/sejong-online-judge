package com.example.demo.service;

import com.example.demo.dto.UpdatePostRequestDto;
import com.example.demo.dto.postBox.CreatePostRequestDto;
import com.example.demo.dto.postBox.PostResponseDto;
import com.example.demo.entity.ContestProblem;
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
        Post post = postRepository.save(CreatePostRequestDto.toEntity(requestDto, user, postBox));
        postBox.update(post);
        postBoxRepository.save(postBox);
        return PostResponseDto.from(post);
    }

    public PostResponseDto updatePost(UpdatePostRequestDto requestDto, Principal principal) {
        Optional<User> userOptional = userRepository.findByUsername(principal.getName());
        if (userOptional.isEmpty()) {
            System.out.println("user가 없습니다. ");
        }
        User user = userOptional.get();
        Optional<Post> postOptional = postRepository.findById(requestDto.getPostId());
        if (postOptional.isEmpty()) {
            System.out.println("post가 없습니다. ");
        }
        Post post = postOptional.get();
        post.update(requestDto.getContent(), requestDto.getSourceCode());
        return PostResponseDto.from(postRepository.save(post));
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
                .map(PostResponseDto::from)
                .toList();
    }


    public List<PostResponseDto> getPostByContestProblem(Long contestProblemId, Principal principal) {
        Optional<User> userOptional = userRepository.findByUsername(principal.getName());
        if (userOptional.isEmpty()) {
            System.out.println("user가 없습니다. ");
        }
        User user = userOptional.get();
        Optional<ContestProblem> contestProblemOptional = contestProblemRepository.findById(contestProblemId);
        if(contestProblemOptional.isEmpty()){
            System.out.println("contestProblem가 없습니다. ");
        }
        ContestProblem contestProblem = contestProblemOptional.get();
        Optional<PostBox> postBoxOptional = postBoxRepository.findByContestProblemAndUser(contestProblem, user);
        if(postBoxOptional.isEmpty()) {
            System.out.println("postBox가 없습니다. ");
        }
        PostBox postBox = postBoxOptional.get();
        return postRepository.findAllByPostBoxAndAuthor(postBox, user).orElse(null).stream()
                .map(PostResponseDto::from)
                .toList();
    }

}
