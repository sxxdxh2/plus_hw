package com.sparta.plus_hw.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String nickname;
    private String password;
}
