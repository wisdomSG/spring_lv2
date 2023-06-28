package com.sparta.springlv2.service;

import com.sparta.springlv2.dto.PostRequestDto;
import com.sparta.springlv2.dto.PostResponseDto;
import com.sparta.springlv2.entity.Post;
import com.sparta.springlv2.entity.User;
import com.sparta.springlv2.jwt.JwtUtil;
import com.sparta.springlv2.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final UserService userService;
    public PostResponseDto createPost(PostRequestDto requestDto,String data) {
        User user = userService.getUsername(data);

        Post post = new Post(requestDto, user);


        Post savePost = postRepository.save(post);


        return new PostResponseDto(savePost);
    }

    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(PostResponseDto::new)
                .toList();
    }


    public PostResponseDto getPost(Long id) {
        Post post = findPost(id);

        return new PostResponseDto(post);
    }





    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.")
        );
    }
}
