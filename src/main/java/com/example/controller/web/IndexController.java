package com.example.controller.web;

import com.example.model.afterLogIn.UserDetailsSession;
import com.example.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class IndexController {
    private final UserDetailsSession userDetailsSession;
    private final PostService postService;

    @GetMapping("/")
    public String goToIndexPage(Model model) {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String allPosts(Model model) {
        model.addAttribute("userDetailsSession", userDetailsSession);
        model.addAttribute("posts", postService.getAllPosts(userDetailsSession.getId()));
        return "index";
    }

    @PostMapping(value = "/createPost")
    public String createPost(Model model, @RequestParam("postContent") String post){
        postService.createPost(post, userDetailsSession.getId());
        return "redirect:/index";
    }
}
