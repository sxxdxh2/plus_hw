package com.sparta.plus_hw.comment.service;

import com.sparta.plus_hw.comment.dto.CommentRequestDto;
import com.sparta.plus_hw.comment.dto.CommentResponseDto;
import com.sparta.plus_hw.comment.dto.UpdateCommentRequestDto;
import com.sparta.plus_hw.comment.entity.Comment;
import com.sparta.plus_hw.comment.entity.CommentLike;
import com.sparta.plus_hw.comment.repository.CommentLikeRepository;
import com.sparta.plus_hw.comment.repository.CommentRepository;
import com.sparta.plus_hw.post.entity.Post;
import com.sparta.plus_hw.post.repository.PostRepository;
import com.sparta.plus_hw.post.service.PostService;
import com.sparta.plus_hw.user.entity.User;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final PostService postService;
    private final PostRepository postRepository;


    public ResponseEntity<String> createComment(CommentRequestDto commentRequestDto, User user) {
        Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        Comment comment = new Comment(commentRequestDto);
        comment.setUser(user);
        comment.setPost(post);

        commentRepository.save(comment);

        return new ResponseEntity<>("댓글 작성 완료", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> updateComment(Long commentId, UpdateCommentRequestDto updateCommentRequestDto, User user) {
        Comment comment = getComment(commentId, user);

        comment.setContents(updateCommentRequestDto.getContents());

        return new ResponseEntity<>("댓글 수정 완료", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteComment(Long commentId, User user) {
        Comment comment = getComment(commentId, user);

        commentRepository.delete(comment);
        return new ResponseEntity<>("댓글 삭제 완료", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> likeComment(Long commentId, User user) {
        Comment comment = getCommentLike(commentId, user);

        CommentLike commentLike = new CommentLike(user, comment);
        commentLikeRepository.save(commentLike);
        return new ResponseEntity<>("💗", HttpStatus.OK);

    }

    private Comment getComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 ID 입니다."));

        if (!user.getId().equals(comment.getUser().getId())) {
            throw new RejectedExecutionException("작성자만 수정할 수 있습니다.");
        }
        return comment;
    }

    private Comment getCommentLike(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 ID 입니다."));

        return comment;
    }

}
