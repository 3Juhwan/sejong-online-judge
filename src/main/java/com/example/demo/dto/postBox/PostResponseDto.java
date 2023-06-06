package com.example.demo.dto.postBox;

import com.example.demo.entity.Post;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

    private Long postId;
    private String content;
    private Long submissionId;
    private String username;

    public static PostResponseDto from(Post post) {
        Long submissionId = null;
        if (post.getSubmission() != null) {
            submissionId = post.getSubmission().getId();
        }
        return PostResponseDto.builder()
                .postId(post.getId())
                .content(post.getContent())
                .submissionId(submissionId)
                .username(post.getAuthor().getUsername())
                .build();
    }
}
