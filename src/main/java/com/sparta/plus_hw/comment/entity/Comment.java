package com.sparta.plus_hw.comment.entity;

import com.sparta.plus_hw.comment.dto.CommentRequestDto;
import com.sparta.plus_hw.post.entity.Post;
import com.sparta.plus_hw.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment(CommentRequestDto commentRequestDto) {
        this.contents = commentRequestDto.getContents();
    }

}
