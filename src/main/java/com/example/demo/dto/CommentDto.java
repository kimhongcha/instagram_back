package com.example.demo.dto;

import com.example.demo.domain.entity.CommentEntity;
import lombok.Builder;

import javax.persistence.Column;
import java.time.LocalDateTime;

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

    public toEntity() {
        return CommentEntity.builder()
                .comment(this.comment)
                .postId(this.postId)
                .build();
    }
}
