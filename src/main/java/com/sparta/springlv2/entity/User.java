package com.sparta.springlv2.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    @Column(nullable = false, unique = true)
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    @Column(nullable = false)
    private String password;


//    @Column(nullable = false)
//    @Enumerated(value = EnumType.STRING) // enum 타입을 데이터베이스에 저장할때 사용하는 애너테이션
//    private UserRoleEnum role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();


    public void addPostList(Post post) {
        this.postList.add(post);
        post.setUser(this);
    }

}
