package com.sparta.plus_hw.post.repository;

import com.sparta.plus_hw.post.entity.Post;
import com.sparta.plus_hw.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreatedAtDesc();
}
