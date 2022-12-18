package com.example.controller;

import com.example.entity.Comment;
import com.example.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}")
    public Optional<Comment> getCommentById(@PathVariable Integer id) {
        return commentService.findById(id);
    }

    @PostMapping
    public Comment commentCreate(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @PutMapping("/{id}")
    public Comment commentUpdate(@PathVariable Integer id, @RequestBody Comment comment) {
        return commentService.updateComment(id, comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
    }
}
