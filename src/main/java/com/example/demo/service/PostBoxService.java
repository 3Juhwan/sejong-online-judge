package com.example.demo.service;

import com.example.demo.dto.postBox.CreatePostBoxRequestDto;
import com.example.demo.dto.postBox.GetPostBoxRequestDto;
import com.example.demo.dto.postBox.PostBoxResponseDto;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostBoxService {

    private final PostBoxRepository postBoxRepository;
    private final UserRepository userRepository;
    private final ContestRepository contestRepository;
    private final CourseRepository courseRepository;
    private final ContestProblemRepository contestProblemRepository;

    public PostBoxResponseDto createPostbox(CreatePostBoxRequestDto requestDto, Principal principal) {
        Course course = courseRepository.findById(requestDto.getCourseId()).orElse(null);
        Contest contest = contestRepository.findById(requestDto.getContestId()).orElse(null);
        ContestProblem contestProblem = contestProblemRepository.findById(requestDto.getContestProblemId()).orElse(null);
        User user = userRepository.findByUsername(principal.getName()).orElse(null);

        PostBox postBox = CreatePostBoxRequestDto.toEntity(requestDto, contestProblem, course, contest, user);
        return PostBoxResponseDto.from(postBoxRepository.save(postBox));
    }

    public void deletePostBox(Long postBoxId, Principal principal) {
        Optional<PostBox> postBoxOptional = postBoxRepository.findById(postBoxId);
        if(postBoxOptional.isEmpty()) {
            System.out.println("postbox가 없습니다. ");
        }
        PostBox postBox = postBoxOptional.get();
        if(principal.getName().equals(postBox.getUser().getUsername())) {
            System.out.println("유저 권한이 다릅니다. ");
        }
        postBoxRepository.delete(postBox);
    }


    public List<PostBoxResponseDto> getPostBox(Long courseId, Principal principal) {
        Optional<User> userOptional = userRepository.findByUsername(principal.getName());
        if (userOptional.isEmpty()) {
            System.out.println("user가 존재하지 않습니다. ");
            return null;
        }
        User user = userOptional.get();
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            System.out.println("course가 존재하지 않습니다. ");
            return null;
        }
        Course course = courseOptional.get();
        if (userOptional.get().getAuthority().equals("ROLE_STUDENT")) {
            return postBoxRepository.findAllByCourseAndUser(course, user).orElse(null).stream()
                    .map(pb -> PostBoxResponseDto.from(pb)).toList();
        } else {
            return postBoxRepository.findByCourse(course).orElse(null).stream()
                    .map(pb -> PostBoxResponseDto.from(pb)).toList();
        }
    }
}
