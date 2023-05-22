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
    private String sourceCode;
    private String username;

    public static PostResponseDto from(Post post) {
        return PostResponseDto.builder()
                .postId(post.getId())
                .content(post.getContent())
                .sourceCode(post.getSourceCode())
                .username(post.getAuthor().getUsername())
                .build();
    }
}
