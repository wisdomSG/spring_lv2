package com.sparta.springlv2.repository;


import com.sparta.springlv2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository  extends JpaRepository<Post, Long> {
}
