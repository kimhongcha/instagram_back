package com.example.demo.domain.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="comment")
public class CommentEntity {
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

}
