package com.sparta.plus_hw.post.dto;

import com.sparta.plus_hw.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetPostResponseDto {
    private final String title;
    private final String nickname;
    private final LocalDateTime createdAt;
    private final String contents;


    public GetPostResponseDto(Post post) {
        this.title = post.getTitle();
        this.nickname = post.getUser().getNickname();
        this.createdAt = post.getCreatedAt();
        this.contents = post.getContents();
    }
}
