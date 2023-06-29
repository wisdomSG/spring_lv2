package com.sparta.springlv2.service;

import com.sparta.springlv2.dto.CommentRequestDto;
import com.sparta.springlv2.dto.CommentResponseDto;
import com.sparta.springlv2.dto.PostRequestDto;
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

    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, String data) {

        // 해당 댓글이 DB에 존재하는지 확인
        Comment comment = findComment(id);

        User user = userService.getUsername(data);

        if(!comment.getUser().getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("해당 사용자는 댓글을 수정할 권한이 없습니다.");
        }

        // 필드 업데이트
        comment.setComments(requestDto.getComment());

        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long id, PostRequestDto requestDto, String data) {
        // 해당 댓글이 DB에 존재하는지 확인
        Comment comment = findComment(id);

        User user = userService.getUsername(data);

        if(!comment.getUser().getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("해당 사용자는 댓글을 수정할 권한이 없습니다.");
        }

        // 댓글 삭제
        commentRepository.delete(comment);

    }

    private Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 댓글은 존재하지 않습니다.")
        );
    }
}
