package com.example.demo.dto;

import com.example.demo.entity.Post;
import com.example.demo.entity.PostBox;
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
    private String sourceCode;

    public static Post toEntity(UpdatePostRequestDto requestDto, User user, PostBox postBox) {
        return Post.builder()
                .author(user)
                .postBox(postBox)
                .content(requestDto.content)
                .sourceCode(requestDto.sourceCode)
                .build();
    }
}
