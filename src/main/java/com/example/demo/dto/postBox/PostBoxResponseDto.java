package com.example.demo.dto.postBox;

import com.example.demo.entity.Post;
import com.example.demo.entity.PostBox;
import lombok.*;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostBoxResponseDto {

    private Long postBoxId;
    private String username;
    private String courseName;
    private String contestName;
    private String contestProblemName;
    private Long contestProblemId;
    private String coverContent;
    private Boolean finished;
    private LocalDateTime modifiedTime;

    public static PostBoxResponseDto from(PostBox postBox) {
        PostBoxResponseDtoBuilder postBoxResponseDtoBuilder = PostBoxResponseDto.builder()
                .postBoxId(postBox.getId())
                .username(postBox.getUser().getUsername())
                .courseName(postBox.getCourse().getTitle())
                .contestName(postBox.getContest().getTitle())
                .contestProblemName(postBox.getContestProblem().getTitle())
                .contestProblemId(postBox.getContestProblem().getId())
                .finished(postBox.getFinished())
                .modifiedTime(postBox.getModifiedTime());
        Post lastPost = postBox.getLastPost();
        if(lastPost != null) {
            postBoxResponseDtoBuilder.coverContent(lastPost.getContent());
        }
        return postBoxResponseDtoBuilder.build();

    }
}

