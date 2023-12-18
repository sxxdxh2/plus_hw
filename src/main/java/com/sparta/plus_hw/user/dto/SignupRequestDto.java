package com.sparta.plus_hw.user.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @Size(min = 3, message = "최소 3자 이상 입력하세요.")
    @Pattern(regexp = "^[a-z0-9]*$", message = "알파벳 소문자, 숫자로 구성되어야 합니다.")
    private String nickname;

    @Size(min = 4, message = "닉네임을 포함하지 않도록 입력하세요.(최소 4자)")
    @Pattern(regexp="^[a-zA-Z0-9]*$", message="알파벳 대소문자, 숫자로 구성되어야 합니다.")
    private String password;

    private String checkPassword;

}
