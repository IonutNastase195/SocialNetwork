package com.example.controller.web;

import com.example.entity.User;
import com.example.model.afterLogIn.UserDetailsSession;
import com.example.model.post.PostRequest;
import com.example.model.user.UserRequest;
import com.example.model.user.UpdateProfileRequest;
import com.example.model.user.UserResponse;
import com.example.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final EventService eventService;
    private final GroupService groupService;
    private final UserService userService;
    private final UserDetailsSession userDetailsSession;
    private final PostService postService;

    @GetMapping("/createUserPage")
    public String goToCreateUserPage(Model model) {
        model.addAttribute("userFromWeb", new UserRequest());
        return "createUserPage";
    }

    @PostMapping("/createUserPage")
    public String createAccount(@ModelAttribute(value = "createUserRequest") UserRequest userRequest, Model model) {
        try {
            userService.createUser(userRequest);
            return "success";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/myProfile")
    public String goToMyProfilePage(Model model) {
        model.addAttribute("userProfile", userService.getCurrentUserProfile());
        return "myProfilePage";
    }

    @GetMapping("/updateProfile")
    public String goToUpdateProfilePage(Model model) {
        User user = userDetailsSession.getUser();
        model.addAttribute("userProfile", user);
        return "updateProfile";
        //redirect to MyProfile page
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute(value = "updateProfileRequest") UpdateProfileRequest updateProfileRequest, Model model) {
        try {
            UserResponse userResponse = userService.updateProfile(updateProfileRequest);
            model.addAttribute("user", userResponse);
            return "profileUpdated";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/createPost")
    public String goToCreatePost(Model model) {
        model.addAttribute("post", postService.createPost(userDetailsSession.getId(), new PostRequest()));
        return "index";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute(value = "post") PostRequest postRequest, Model model) {
        try {
            postService.createPost(userDetailsSession.getId(),postRequest);
            return "index";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/groupsPage")
    public String goToGroupsPage(Model model) {
        model.addAttribute("groups", groupService.getCurrentUserGroups());
        return "groupsPage";
    }

    @GetMapping("/events")
    public String goToEventsPage(Model model) {
        model.addAttribute("events", eventService.getCurrentUserEvents());
        return "eventsPage";
    }

    @GetMapping("/allUsersPage")
    public String goToAllUsersPage(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "allUsersPage";
    }

}


