package com.example.demo.dto.postBox;

import com.example.demo.entity.PostBox;
import lombok.*;

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
    private Boolean finished;
    private LocalDateTime modifiedTime;

    public static PostBoxResponseDto from(PostBox postBox) {
        return PostBoxResponseDto.builder()
                .postBoxId(postBox.getId())
                .username(postBox.getUser().getUsername())
                .courseName(postBox.getCourse().getTitle())
                .contestName(postBox.getContest().getTitle())
                .contestProblemName(postBox.getContestProblem().getTitle())
                .finished(postBox.getFinished())
                .modifiedTime(postBox.getModifiedTime())
                .build();
    }
}

