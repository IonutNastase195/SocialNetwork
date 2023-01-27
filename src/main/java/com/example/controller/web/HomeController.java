package com.example.controller.web;

import com.example.model.afterLogIn.UserDetailsSession;
import com.example.model.post.PostRequest;
import com.example.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final GroupService groupService;
    private final UserDetailsSession userDetailsSession;
    private final PostService postService;


//    @GetMapping("/createPost")
//    public String goToCreatePost(Model model) {
//        model.addAttribute("post", postService.createPost(userDetailsSession.getId(), new PostRequest()));
//        return "index";
//    }

//    @PostMapping("/createPost")
//    public String createPost(@ModelAttribute(value = "post") PostRequest postRequest, Model model) {
//        try {
//            postService.createPost(userDetailsSession.getId(), postRequest);
//            return "index";
//        } catch (Exception e) {
//            model.addAttribute("error", e.getMessage());
//            return "error";
//        }
//    }

    @GetMapping("/groupsPage")
    public String goToGroupsPage(Model model) {
        model.addAttribute("groups", groupService.getCurrentUserGroups());
        return "groupsPage";
    }

}


