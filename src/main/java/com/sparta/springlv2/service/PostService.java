package com.sparta.springlv2.service;

import com.sparta.springlv2.dto.PostRequestDto;
import com.sparta.springlv2.dto.PostResponseDto;
import com.sparta.springlv2.entity.Post;
import com.sparta.springlv2.entity.User;
import com.sparta.springlv2.jwt.JwtUtil;
import com.sparta.springlv2.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, String data) {
        // 해당 게시물이 DB에 존재하는지 확인
        Post post = findPost(id);

        User user = userService.getUsername(data);

        if(!post.getUser().getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("해당 사용자는 게시물을 수정할 권한이 없습니다.");
        }

        // 필드 업데이트
        post.setTitle(requestDto.getTitle());
        post.setContents(requestDto.getContents());

        return new PostResponseDto(post);

    }

    public void deletePost(Long id, PostRequestDto requestDto, String data) {
        // 해당 게시물이 DB에 존재하는지 확인
        Post post = findPost(id);

        User user = userService.getUsername(data);

        if(!post.getUser().getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("해당 사용자는 게시물을 삭제할 권한이 없습니다.");
        }

        // 포스트 삭제
        postRepository.delete(post);
    }



    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.")
        );
    }

}
