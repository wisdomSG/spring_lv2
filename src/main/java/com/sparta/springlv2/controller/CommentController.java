package com.sparta.springlv2.controller;

import com.sparta.springlv2.dto.CommentRequestDto;
import com.sparta.springlv2.dto.CommentResponseDto;
import com.sparta.springlv2.dto.PostResponseDto;
import com.sparta.springlv2.jwt.JwtUtil;
import com.sparta.springlv2.service.CommentService;
import io.jsonwebtoken.Jwt;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final JwtUtil jwtUtil;

    // 댓글 저장
    @PostMapping("/comment")
    public CommentResponseDto createComment(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String data, @RequestBody CommentRequestDto requestDto) {
        data = jwtUtil.inspectToken(data);

        return commentService.createComment(requestDto, data);
    }
}
