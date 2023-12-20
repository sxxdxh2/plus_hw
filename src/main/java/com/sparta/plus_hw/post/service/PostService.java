package com.sparta.plus_hw.post.service;

import com.sparta.plus_hw.post.dto.GetPostResponseDto;
import com.sparta.plus_hw.post.dto.PostRequestDto;
import com.sparta.plus_hw.post.dto.PostResponseDto;
import com.sparta.plus_hw.post.entity.Post;
import com.sparta.plus_hw.post.repository.PostRepository;
import com.sparta.plus_hw.user.entity.User;
import com.sparta.plus_hw.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Transactional
    public ResponseEntity<String> createPost(PostRequestDto postRequestDto, User user) {
        postRepository.save(new Post(postRequestDto, user));
        return new ResponseEntity<>("게시글 작성 완료", HttpStatus.OK);
    }


    public List<PostResponseDto> getPostList() {
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> postResponseDtoList = postList.stream().map(PostResponseDto::new).toList();
        return postResponseDtoList;
    }

    public GetPostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        return new GetPostResponseDto(post);
    }

    @Transactional
    public ResponseEntity<String> updatePost(Long postId, PostRequestDto postRequestDto, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        if(post.getUser().getId().equals(user.getId())){
            post.update(postRequestDto);
        } else {throw new IllegalArgumentException("권한이 없습니다.");}

        return new ResponseEntity<>("게시글 수정 완료", HttpStatus.OK);
    }

    public ResponseEntity<String> deletePost(Long postId,User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        if(post.getUser().getId().equals(user.getId())){
            postRepository.delete(post);
        } else {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        return new ResponseEntity<>("게시글 삭제 완료", HttpStatus.OK);
    }


}
