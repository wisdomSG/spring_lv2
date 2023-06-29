package com.sparta.springlv2.entity;

import com.sparta.springlv2.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "posts")
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String  title;
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    public Post(PostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.user = user;

    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
    private List<Comment> commentList = new ArrayList<>();


}