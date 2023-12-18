package com.sparta.plus_hw.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.plus_hw.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto {
    private String msg;
    private int status;

}
