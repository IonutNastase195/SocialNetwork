package com.example.controller;

import com.example.entity.Post;
import com.example.entity.User;
import com.example.service.implementation.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Integer id){
        return postService.findById(id);
    }

    @PostMapping
    public Post postCreate(@RequestBody Post post){
        return postService.create(post);
    }

    @PutMapping("/{id}")
    public User updatePost(@PathVariable Integer id, @RequestBody Post post) {
        return postService.update(id, post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Integer id) {
        postService.delete(id);
    }
}
