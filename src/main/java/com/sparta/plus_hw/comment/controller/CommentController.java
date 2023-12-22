package com.sparta.plus_hw.comment.controller;

import com.sparta.plus_hw.comment.dto.CommentRequestDto;
import com.sparta.plus_hw.comment.dto.CommentResponseDto;
import com.sparta.plus_hw.comment.dto.UpdateCommentRequestDto;
import com.sparta.plus_hw.comment.service.CommentService;
import com.sparta.plus_hw.user.security.UserDetailsImpl;
import com.sun.jdi.request.DuplicateRequestException;
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
        return commentService.createComment(commentRequestDto, userDetails.getUser());
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<String> updateComment(
            @PathVariable Long commentId,
            @RequestBody UpdateCommentRequestDto updateCommentRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return commentService.updateComment(commentId, updateCommentRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return commentService.deleteComment(commentId, userDetails.getUser());
    }

    @PostMapping("/{commentId}/like")
    public ResponseEntity<String> likeComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return commentService.likeComment(commentId, userDetails.getUser());
    }

    @DeleteMapping("/{postId}/like")
    public ResponseEntity<String> deleteLikeComment(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return commentService.deleteLikeComment(postId, userDetails.getUser());
    }

}
