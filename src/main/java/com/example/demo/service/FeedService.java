package com.example.demo.service;

import com.example.demo.domain.entity.Comment;
import com.example.demo.domain.entity.Feed;
import com.example.demo.domain.entity.Member;
import com.example.demo.domain.repository.FeedRepository;
import com.example.demo.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FeedService {

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CommentService commentService;

    public Feed getOne(Long id){
        return this.feedRepository.getOne(id);
    }

    public String findAuthorByid(Long id){
        Member member= memberRepository.getOne(id);
        return member.getName();
    }

    public List<Comment> findComments(Long id){
        Feed feed = feedRepository.getOne(id);
        return feed.getComments();
    }

    public List<Feed> findAllPost(){
        return feedRepository.findAll();
    }

    
    public List<Feed> findAllPostByAuthor(Long id){
        return feedRepository.findByAuthor(id);
    }

    @Transactional
    public void deleteOne(Long id){
        //commentService.deleteAllByPostId(id); //post에 딸린 comments 먼저 지우
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
