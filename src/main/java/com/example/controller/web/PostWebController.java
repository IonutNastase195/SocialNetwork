package com.example.controller.web;

import com.example.model.post.PostRequest;
import com.example.model.post.PostUpdateRequest;
import com.example.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/2")
public class PostWebController {

    private final PostService postService;

    @GetMapping(value = "/posts/{id}")
    public String goToPostPage(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "postPage";
    }

    @GetMapping("/{id}")
    public String goToPostById(@PathVariable Integer id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        return "postByIdPage";
    }

    @PostMapping("/users/{userId}/posts")
    public String createPost(@PathVariable Integer userId, @ModelAttribute PostRequest postRequest, Model model) {
        model.addAttribute("post", postService.createPost(userId, postRequest));
        return "postByIdPage";
    }

    @PostMapping("/{id}")
    public String updatePostById(@PathVariable Integer id, @ModelAttribute PostUpdateRequest postUpdateRequest, Model model) {
        postService.updatePostById(postUpdateRequest);
        model.addAttribute("post", postService.getPostById(id));
        return "postByIdPage";
    }

    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Integer id, Model model) {
        postService.deletePost(id);
        model.addAttribute("posts", postService.getAllPosts());
        return "postPage";
    }
}
