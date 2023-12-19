package com.sparta.plus_hw.comment.repository;

import com.sparta.plus_hw.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
