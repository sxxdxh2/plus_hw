package com.sparta.plus_hw.post.service;

import com.sparta.plus_hw.post.dto.GetPostResponseDto;
import com.sparta.plus_hw.post.dto.PostRequestDto;
import com.sparta.plus_hw.post.dto.PostResponseDto;
import com.sparta.plus_hw.post.entity.Post;
import com.sparta.plus_hw.post.repository.PostRepository;
import com.sparta.plus_hw.user.entity.User;
import com.sparta.plus_hw.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {
        Post post = postRepository.save(new Post(postRequestDto, user));
        return new PostResponseDto(post);
    }


    public List<PostResponseDto> getPostList() {
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> postResponseDtoList = postList.stream().map(PostResponseDto::new).toList();
        return postResponseDtoList;
    }

    public GetPostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("글이 존재하지 않습니다.")
        );
        return new GetPostResponseDto(post);
    }









}
