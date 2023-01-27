package com.example.controller.rest;

import com.example.entity.Comment;
import com.example.mapper.CommentMapper;
import com.example.model.comment.CommentRequest;
import com.example.model.comment.CommentResponse;
import com.example.model.comment.CommentUpdate;
import com.example.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/comment")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments")
    public List<CommentResponse> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/comments/{id}")
    public CommentResponse getCommentById(@PathVariable Integer id) {
        return commentService.getCommentById(id);
    }

    @PostMapping("/comments")
    public CommentResponse createComment(@RequestBody CommentRequest commentRequest) {
        return commentService.createComment(commentRequest);
    }

    @PutMapping("/comments/{id}")
    public void updateCommentById(@PathVariable Integer id, @RequestBody CommentUpdate commentUpdate) {
        commentService.updateCommentById(id, commentUpdate);
    }

    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
    }
}
