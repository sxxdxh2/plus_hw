package com.sparta.plus_hw.post.dto;

import com.sparta.plus_hw.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private final Long postId;
    private final String title;
    private final String nickname;
    private final LocalDateTime createdAt;

    public PostResponseDto(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.nickname = post.getUser().getNickname();
        this.createdAt = post.getCreatedAt();
    }
}
