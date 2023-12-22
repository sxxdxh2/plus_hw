package com.sparta.plus_hw.post.entity;

import com.sparta.plus_hw.comment.entity.Comment;
import com.sparta.plus_hw.post.dto.PostRequestDto;
import com.sparta.plus_hw.post.timestamped.Timestamped;
import com.sparta.plus_hw.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post") // 댓글리스트를 불러오기 위한
    private List<Comment> commentList = new ArrayList<>();

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;


    public Post(PostRequestDto postRequestDto, User user) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
        this.user = user;
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
    }

}
