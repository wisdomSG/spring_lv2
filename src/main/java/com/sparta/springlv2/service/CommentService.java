package com.sparta.springlv2.service;

import com.sparta.springlv2.dto.CommentRequestDto;
import com.sparta.springlv2.dto.CommentResponseDto;
import com.sparta.springlv2.dto.PostResponseDto;
import com.sparta.springlv2.entity.Comment;
import com.sparta.springlv2.entity.Post;
import com.sparta.springlv2.entity.User;
import com.sparta.springlv2.jwt.JwtUtil;
import com.sparta.springlv2.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserService userService;

    private final PostService postService;


    public CommentResponseDto createComment(CommentRequestDto requestDto, String data) {
        User user = userService.getUsername(data);

        Post post = postService.findPost(requestDto.getPostId());

        Comment comment = new Comment(requestDto, post, user);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }
}
