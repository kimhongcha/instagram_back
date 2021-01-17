package com.example.demo.domain.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="feed")
public class FeedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 200)
    private String content;

    @Column(length = 200, nullable = false)
    private String image;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="post_id")
    private List<CommentEntity> comments;


}
