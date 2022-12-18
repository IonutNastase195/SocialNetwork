package com.example.controller;

import com.example.entity.Post;
import com.example.entity.User;
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
@RequestMapping("/post")
public class PostController {

    private final PostService postService;


    @GetMapping("find/{postId}")
    public PostResponse getPostById(@PathVariable Integer postId){
        return postService.getPostById(postId);
    }

    @PostMapping ("/create")
    public PostResponse createPost(@RequestBody PostRequest postRequest){
        return postService.createPost(postRequest);
    }

    @PatchMapping("update")
    public void updatePostById(@RequestBody @Valid PostUpdateRequest postUpdateRequest){
        postService.updatePostById(postUpdateRequest);
    }

    @DeleteMapping("delete/{postId}")
    public void deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
    }

    @GetMapping("find-all-posts")
    public List<PostResponse> getAllPosts(){
        return postService.allPosts();
    }

}
