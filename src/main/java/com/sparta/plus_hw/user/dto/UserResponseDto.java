package com.sparta.plus_hw.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.plus_hw.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
    private Long id;
    private String nickname;
    private String password;


    public UserResponseDto(User user) {
        this.nickname = user.getNickname();
        this.password = user.getPassword();
    }

}
