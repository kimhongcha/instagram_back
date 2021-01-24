package com.example.demo.dto;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class CommentDto {
    private Long id;

    private Long postId;

    private String comment;

    private LocalDateTime createdAt;
}
