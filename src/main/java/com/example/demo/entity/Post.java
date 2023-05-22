package com.example.demo.entity;

import com.example.demo.entity.util.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_box_id")
    private PostBox postBox;

    private String content;

    private String sourceCode;

    public void update(String content, String sourceCode) {
        if(content != null) {
            this.content = content;
        }
        if(sourceCode != null) {
            this.sourceCode = sourceCode;
        }
    }

}
