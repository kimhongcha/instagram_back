package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/create")
    private Long createComment(CommentDto commentDto) {
        Long id = commentDto.getPostId();
        commentService.createComment(commentDto);

        return id;
    }

    @DeleteMapping(value="delete/{id}")
    public Long deleteComment(@PathVariable("id") Long id){
        Long pid = new Long(1);//ommentService.getPostIdByCommentId(id);
        //commentService.deleteOne(id);

        return pid;
    }

}
