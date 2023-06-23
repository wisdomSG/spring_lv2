package com.sparta.springlv2.controller;

import com.sparta.springlv2.dto.PostRequestDto;
import com.sparta.springlv2.dto.PostResponseDto;
import com.sparta.springlv2.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
