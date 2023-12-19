package com.sparta.plus_hw.post.dto;

import com.sparta.plus_hw.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdatePostResponseDto {
    private final String title;
    private final String nickname;
    private final LocalDateTime modifiedAt;
    private final String contents;


    public UpdatePostResponseDto(Post post) {
        this.title = post.getTitle();
        this.nickname = post.getUser().getNickname();
        this.modifiedAt = post.getModifiedAt();
        this.contents = post.getContents();
    }
}
