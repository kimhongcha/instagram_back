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

    @Column
    private String comment;

    @Column
    private LocalDateTime regDate;

}
