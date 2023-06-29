package com.sparta.springlv2.dto;

import com.sparta.springlv2.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {
    private Long id;
    private String comments;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String username;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comments = comment.getComments();
        this.username = comment.getUser().getUsername();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }

}
