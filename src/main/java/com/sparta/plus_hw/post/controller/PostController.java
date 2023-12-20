package com.sparta.plus_hw.post.controller;

import com.sparta.plus_hw.post.dto.GetPostResponseDto;
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
    public ResponseEntity<String> createPost(
            @RequestBody PostRequestDto postRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return postService.createPost(postRequestDto, userDetails.getUser());
    }

    //조회
    @GetMapping("")
    public List<PostResponseDto> getPostList() {
    return postService.getPostList();
}

    @GetMapping("/{postId}")
    public ResponseEntity<GetPostResponseDto> getPost(@PathVariable Long postId){
        GetPostResponseDto getPostResponseDto = postService.getPost(postId);
        return ResponseEntity.ok(getPostResponseDto);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<String> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto postRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return postService.updatePost(postId, postRequestDto,userDetails.getUser());
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        return postService.deletePost(postId,userDetails.getUser());
    }

}
