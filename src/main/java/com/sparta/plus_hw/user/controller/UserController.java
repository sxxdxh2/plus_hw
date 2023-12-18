package com.sparta.plus_hw.user.controller;

import com.sparta.plus_hw.user.dto.ApiResponseDto;
import com.sparta.plus_hw.user.dto.SignupRequestDto;
import com.sparta.plus_hw.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponseDto signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return new ApiResponseDto("회원가입 성공", HttpStatus.OK.value());
    }



}
