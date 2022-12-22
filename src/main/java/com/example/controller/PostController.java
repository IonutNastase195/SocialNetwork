package com.example.controller;

import com.example.entity.Post;
import com.example.entity.User;
import com.example.mapper.PostMapper;
import com.example.model.post.PostRequest;
import com.example.model.post.PostResponse;
import com.example.model.post.PostUpdateRequest;
import com.example.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping("/posts")
    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/posts/{id}")
    public PostResponse getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    @PostMapping("/users/{userId}/posts")
    public PostResponse createPost(@PathVariable Integer userId, @RequestBody PostRequest postRequest) {
        return postService.createPost(userId, postRequest);
    }

    @PutMapping("/posts/{id}")
    public void updatePostById(@PathVariable Integer id, @RequestBody PostUpdateRequest postUpdateRequest) {
        postService.updatePostById(postUpdateRequest);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
    }
}
