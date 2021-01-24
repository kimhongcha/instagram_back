package com.example.demo.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name="post_id")
    private Long postId;


    @Column
    private String comment;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Builder
    public Comment(Long id, Long postId, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.postId = postId;
        this.comment = comment;
        this.createdAt = createdAt;
    }
}
