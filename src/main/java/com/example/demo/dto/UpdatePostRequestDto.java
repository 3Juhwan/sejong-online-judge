package com.example.demo.dto;

import com.example.demo.entity.Post;
import com.example.demo.entity.PostBox;
import com.example.demo.entity.Submission;
import com.example.demo.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostRequestDto {

    private Long postId;
    private String content;
    private Long submissionId;

    public static Post toEntity(UpdatePostRequestDto requestDto, User user, PostBox postBox, Submission submission) {
        return Post.builder()
                .author(user)
                .postBox(postBox)
                .content(requestDto.content)
                .submission(submission)
                .build();
    }
}
