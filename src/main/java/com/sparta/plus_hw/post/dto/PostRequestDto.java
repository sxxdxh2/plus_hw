package com.sparta.plus_hw.post.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SecondaryRow;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String contents;
}
