package com.example.demo.dto.postBox;

import com.example.demo.entity.Post;
import com.example.demo.entity.PostBox;
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
    private String sourceCode;

    public static Post toEntity(CreatePostRequestDto requestDto, User user, PostBox postBox) {
        return Post.builder()
                .author(user)
                .postBox(postBox)
                .content(requestDto.content)
                .sourceCode(requestDto.sourceCode)
                .build();
    }
}
