package com.sparta.plus_hw.comment.repository;

import com.sparta.plus_hw.comment.entity.Comment;
import com.sparta.plus_hw.comment.entity.CommentLike;
import com.sparta.plus_hw.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}
