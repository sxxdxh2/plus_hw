package com.sparta.plus_hw.post.dto;

import com.sparta.plus_hw.comment.dto.CommentResponseDto;
import com.sparta.plus_hw.comment.entity.Comment;
import com.sparta.plus_hw.post.entity.Post;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GetPostResponseDto { //단건조회 response dto
    private final String title;
    private final String nickname;
    private final LocalDateTime createdAt;
    private final String contents;
    private final List<CommentResponseDto> commentList;


    public GetPostResponseDto(Post post) {
        this.title = post.getTitle();
        this.nickname = post.getUser().getNickname();
        this.createdAt = post.getCreatedAt();
        this.contents = post.getContents();
        this.commentList = new ArrayList<>();
        for(Comment comment : post.getCommentList()){
            commentList.add(new CommentResponseDto(comment));
        }
    }
}
