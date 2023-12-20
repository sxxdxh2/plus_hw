package com.sparta.plus_hw.user.controller;

import com.sparta.plus_hw.user.dto.ApiResponseDto;
import com.sparta.plus_hw.user.dto.LoginRequestDto;
import com.sparta.plus_hw.user.dto.SignupRequestDto;
import com.sparta.plus_hw.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity <ApiResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
    }















}
