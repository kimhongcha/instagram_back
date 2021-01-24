package com.example.demo.service;

import com.example.demo.domain.entity.CommentEntity;
import com.example.demo.domain.entity.FeedEntity;
import com.example.demo.domain.repository.CommentRepository;
import com.example.demo.domain.repository.FeedRepository;
import com.example.demo.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FeedRepository feedRepository;


    public void uploadComment(CommentDto commentDto) {
        commentDto.setCreatedAt(new Date());

        this.commentRepository.save(commentDto.toEntity());
    }


    @Transactional
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    public void deleteAllByFeedId(Long id) {
        FeedEntity feed = feedRepository.getOne(id);
        commentRepository.deleteAll(feed.getComments());
    }

    public Long getFeedIdByCommentId(Long id) {
        CommentEntity commentEntity = commentRepository.getOne(id);

        return commentEntity.getPostId();
    }
}
