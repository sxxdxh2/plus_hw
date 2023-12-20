package com.sparta.plus_hw.comment.controller;

import com.sparta.plus_hw.comment.dto.CommentRequestDto;
import com.sparta.plus_hw.comment.dto.CommentResponseDto;
import com.sparta.plus_hw.comment.dto.UpdateCommentRequestDto;
import com.sparta.plus_hw.comment.service.CommentService;
import com.sparta.plus_hw.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> createComment(
            @RequestBody CommentRequestDto commentRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.createComment(commentRequestDto, userDetails.getUser());

        return new ResponseEntity<>("댓글 작성 완료", HttpStatus.OK);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<String> updateComment(
            @PathVariable Long commentId,
            @RequestBody UpdateCommentRequestDto updateCommentRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.updateComment(commentId, updateCommentRequestDto, userDetails.getUser());

        return new ResponseEntity<>("댓글 수정 완료", HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
            commentService.deleteComment(commentId, userDetails.getUser());

            return new ResponseEntity<>("댓글 삭제 완료", HttpStatus.OK);
    }

}
