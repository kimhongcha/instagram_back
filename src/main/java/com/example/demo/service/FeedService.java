package com.example.demo.service;

import com.example.demo.domain.entity.CommentEntity;
import com.example.demo.domain.entity.FeedEntity;
import com.example.demo.domain.entity.MemberEntity;
import com.example.demo.domain.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FeedService {

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    CommentService commentService;

    public FeedEntity getOne(Long id){
        return this.feedRepository.getOne(id);
    }

    public String findAuthorByid(Long id){
        MemberEntity member= memberRepository.getOne(id);
        return member.getNickname();
    }

    public List<CommentEntity> findComments(Long id){
        FeedEntity feed = feedRepository.getOne(id);
        return feed.getComments();
    }

    public List<FeedEntity> findAllPost(){
        return feedRepository.findAll();
    }

    
    public List<FeedEntity> findAllPostByAuthor(Long id){
        return feedRepository.findByAuthor(id);
    }

    @Transactional
    public void deleteOne(Long id){
        commentService.deleteAllByPostId(id); //post에 딸린 comments 먼저 지우
        String filename = feedRepository.getOne(id).getImage();

        /**
         *  파일 삭제 기능 구현
         */
//        if(filename!=null) {
//            int idx = filename.lastIndexOf("/");
//            s3Service.delete(filename.substring(idx + 1));
//        } //S3에서 파일 지우기
        feedRepository.deleteById(id);

    }


}
