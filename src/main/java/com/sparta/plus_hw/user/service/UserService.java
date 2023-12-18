package com.sparta.plus_hw.user.service;

import com.sparta.plus_hw.common.JwtUtil;
import com.sparta.plus_hw.user.dto.ApiResponseDto;
import com.sparta.plus_hw.user.dto.LoginRequestDto;
import com.sparta.plus_hw.user.dto.UserResponseDto;
import com.sparta.plus_hw.user.repository.UserRepository;
import com.sparta.plus_hw.user.dto.SignupRequestDto;
import com.sparta.plus_hw.user.entity.User;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public UserResponseDto signup(SignupRequestDto signupRquestDto) {
        String nickname = signupRquestDto.getNickname();
        String password = passwordEncoder.encode(signupRquestDto.getPassword());
        String checkPassword = passwordEncoder.encode(signupRquestDto.getCheckPassword());

        Optional<User> checkNickname = userRepository.findByNickname(nickname);
        if (checkNickname.isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
         //   return new ApiResponseDto("중복된 사용자가 존재합니다.", HttpStatus.BAD_REQUEST.value());
        }

        if(!passwordEncoder.matches(signupRquestDto.getPassword(), checkPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }


        User user = new User(nickname, password);
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String nickname = loginRequestDto.getNickname();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByNickname(nickname)
                .orElseThrow(()-> new IllegalArgumentException("닉네임 또는 패스워드를 확인해주세요."));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("닉네임 또는 패스워드를 확인해주세요.");
        }

        jwtUtil.addJwtToCookie(jwtUtil.createToken(user.getNickname()), response);
        return new UserResponseDto(user);
    }









}
