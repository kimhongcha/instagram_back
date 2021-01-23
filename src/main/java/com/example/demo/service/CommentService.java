package com.example.demo.service;

import com.example.demo.domain.entity.FeedEntity;
import com.example.demo.domain.repository.CommentRepository;
import com.example.demo.domain.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FeedRepository feedRepository;


    public void uploadComment() {

    }


    @Transactional
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    public void deleteAllByFeedId(Long id) {
        FeedEntity feed = feedRepository.getOne(id);
        commentRepository.deleteAll(feed.getComments());
    }
}
