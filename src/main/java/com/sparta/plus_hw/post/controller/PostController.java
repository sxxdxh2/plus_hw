package com.sparta.plus_hw.post.controller;

import com.sparta.plus_hw.post.dto.PostRequestDto;
import com.sparta.plus_hw.post.dto.PostResponseDto;
import com.sparta.plus_hw.post.service.PostService;
import com.sparta.plus_hw.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    //작성
    @PostMapping("")
    public ResponseEntity<String> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.createPost(postRequestDto, userDetails.getUser());
        return new ResponseEntity<>("게시물 작성 성공", HttpStatus.OK);
    }

//조회
@GetMapping("")
public List<PostResponseDto> getPostList() {
    return postService.getPostList();
}



}
