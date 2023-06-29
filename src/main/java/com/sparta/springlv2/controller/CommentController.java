package com.sparta.springlv2.controller;

import com.sparta.springlv2.dto.*;
import com.sparta.springlv2.jwt.JwtUtil;
import com.sparta.springlv2.service.CommentService;
import io.jsonwebtoken.Jwt;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    // 댓글 수정
    @PutMapping("comment/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String data, @RequestBody CommentRequestDto requestDto) {
        data = jwtUtil.inspectToken(data);

        return commentService.updateComment(id, requestDto, data);
    }

    // 댓글 삭제
    @DeleteMapping("commnet/{id}")
    public ResponseEntity<StatusResponseDto> DeleteComment(@PathVariable Long id, @CookieValue(JwtUtil.AUTHORIZATION_HEADER) String data, @RequestBody PostRequestDto requestDto) {
        data = jwtUtil.inspectToken(data);

        commentService.deleteComment(id, requestDto, data);

        String msg = "게시물 삭제 성공";
        StatusResponseDto responseDto = new StatusResponseDto(msg, HttpStatus.OK.value());

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }
}
