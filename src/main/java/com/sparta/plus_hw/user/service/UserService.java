package com.sparta.plus_hw.user.service;

import com.sparta.plus_hw.common.JwtUtil;
import com.sparta.plus_hw.user.dto.LoginRequestDto;
import com.sparta.plus_hw.user.dto.SignupRequestDto;
import com.sparta.plus_hw.user.entity.User;
import com.sparta.plus_hw.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> signup(SignupRequestDto signupRquestDto) {
        String nickname = signupRquestDto.getNickname();
        String password = passwordEncoder.encode(signupRquestDto.getPassword());
        String checkPassword = passwordEncoder.encode(signupRquestDto.getCheckPassword());

        Optional<User> checkNickname = userRepository.findByNickname(nickname);
        if (checkNickname.isPresent()) {
            return new ResponseEntity<>("중복된 닉네임입니다.", HttpStatus.BAD_REQUEST);
        }

        if(password.contains(nickname)) {
            return new ResponseEntity<>("비밀번호는 닉네임을 포함할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        if(!passwordEncoder.matches(signupRquestDto.getPassword(), checkPassword)) {
            return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        User user = new User(nickname, password);
        userRepository.save(user);
        return new ResponseEntity<>("회원가입 성공", HttpStatus.ACCEPTED);
    }



    @Transactional
    public ResponseEntity<String> login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String nickname = loginRequestDto.getNickname();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByNickname(nickname)
                .orElseThrow(()-> new IllegalArgumentException("닉네임을 확인해주세요."));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        jwtUtil.addJwtToCookie(jwtUtil.createToken(user.getNickname()), response);
        return new ResponseEntity<>("로그인 성공", HttpStatus.ACCEPTED);
    }

}
