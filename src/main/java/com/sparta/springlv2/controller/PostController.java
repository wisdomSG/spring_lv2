package com.sparta.springlv2.controller;

import com.sparta.springlv2.dto.PostRequestDto;
import com.sparta.springlv2.dto.PostResponseDto;
import com.sparta.springlv2.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // HTTP Response Body에 객체데이터를 JSON 형식으로 반환하는 컨트롤러
@RequestMapping("/api")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시물 저장
    @PostMapping("/post")
    public PostResponseDto create(@RequestBody PostRequestDto requestDto) {
        return  postService.createPost(requestDto);
    }

    // 전체 게시물 조회
//    @GetMapping("/posts")
//    public List<PostResponseDto> getPosts() {
//        return postService.getPosts();
//    }

    // 선택한 게시물 조회
    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // 게시물 수정
//    @PutMapping("/post/{id}")
//    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
//        return postService.updatePost(id, requestDto);
//    }

}
