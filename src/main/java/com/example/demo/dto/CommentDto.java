package com.example.demo.dto;

import com.example.demo.domain.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private Long id;

    private Long postId;

    private String comment;

    private LocalDateTime createdAt;

    @Builder
    public CommentDto(Long id, Long postId, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.postId = postId;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Comment toEntity() {
        return Comment.builder()
                .comment(this.comment)
                .postId(this.postId)
                .build();
    }
}
