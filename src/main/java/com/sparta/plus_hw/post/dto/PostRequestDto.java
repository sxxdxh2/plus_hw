package com.sparta.plus_hw.post.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SecondaryRow;

@Getter
@Setter
public class PostRequestDto {
    @Size(max = 500)
    private String title;
    @Size(max = 5000)
    private String contents;
}
