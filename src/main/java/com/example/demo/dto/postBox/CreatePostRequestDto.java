package com.example.demo.dto.postBox;

import com.example.demo.entity.Post;
import com.example.demo.entity.PostBox;
import com.example.demo.entity.Submission;
import com.example.demo.entity.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostRequestDto {

    private Long postBoxId;
    private String content;
    private Long submissionId;

    public static Post toEntity(CreatePostRequestDto requestDto, User user, PostBox postBox, Submission submission) {
        Post.PostBuilder postBuilder = Post.builder()
                .author(user)
                .postBox(postBox)
                .content(requestDto.content);
        if(submission!=null) {
            return postBuilder.submission(submission).build();
        } else {
            return postBuilder.build();
        }
    }
}
