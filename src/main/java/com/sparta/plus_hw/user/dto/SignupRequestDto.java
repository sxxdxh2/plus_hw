package com.sparta.plus_hw.user.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @Pattern(regexp = "^[a-zA-Z0-9]{3,10}$", message = "닉네임은 최소 3자 이상, 알파벳 대소문자와 숫자로 구성되어야 합니다.")
    private String nickname;

    @Pattern(regexp="^[a-zA-Z0-9]{4,12}$", message="비밀번호는 최소 4자 이상, 알파벳 대소문자와 숫자로 구성되어야 합니다.")
    private String password;

    private String checkPassword;

}
