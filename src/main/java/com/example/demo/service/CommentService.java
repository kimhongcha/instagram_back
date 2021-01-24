package com.example.demo.service;

import com.example.demo.domain.entity.Comment;
import com.example.demo.domain.entity.Feed;
import com.example.demo.domain.repository.CommentRepository;
import com.example.demo.domain.repository.FeedRepository;
import com.example.demo.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FeedRepository feedRepository;


    public Comment createComment(CommentDto commentDto) {

        commentDto.setCreatedAt(LocalDateTime.now());
        return this.commentRepository.save(commentDto.toEntity());
    }


    @Transactional
    public void deleteById(Long id) {

        commentRepository.deleteById(id);
    }

    public void deleteAllByFeedId(Long id) {
        Feed feed = feedRepository.getOne(id);
        commentRepository.deleteAll(feed.getComments());
    }

    public Long getFeedIdByCommentId(Long id) {
        Comment commentEntity = commentRepository.getOne(id);

        return commentEntity.getPostId();
    }
}
