package com.example.controller;

import com.example.entity.Comment;
import com.example.service.implementation.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Integer id){
        return commentService.findById(id);
    }

    @PostMapping
    public Comment commentCreate(@RequestBody Comment comment){
        return commentService.create(comment);
    }

    @PutMapping("/{id}")
    public Comment commentUpdate(@PathVariable Integer id, @RequestBody Comment comment){
        return commentService.commentUpdate(id,comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);
    }
}
