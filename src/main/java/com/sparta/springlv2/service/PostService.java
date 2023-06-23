package com.sparta.springlv2.service;

import com.sparta.springlv2.dto.PostRequestDto;
import com.sparta.springlv2.dto.PostResponseDto;
import com.sparta.springlv2.entity.Post;
import com.sparta.springlv2.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto requestDto) {

        Post post = new Post(requestDto);

        Post savePost = postRepository.save(post);

        return new PostResponseDto(savePost);
    }
}
