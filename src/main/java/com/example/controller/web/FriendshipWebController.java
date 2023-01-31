package com.example.controller.web;

import com.example.entity.User;
import com.example.model.afterLogIn.UserDetailsSession;
import com.example.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class FriendshipWebController {
    private final UserService userService;
    private final UserDetailsSession userDetailsSession;


    @PostMapping("/addFriendship")
    public String addFriend(@ModelAttribute(value = "userId") Integer userId) {
        if (userId != null) {
            User user = userDetailsSession.getUser();
            userService.addFriend(user.getId(), userId);
            userDetailsSession.setUser(userService.getUserById(user.getId()));
        }
        return "redirect:/allUsersPage";
    }

    @GetMapping("/friends")
    public String goToFriendsPage(Model model) {
        Integer userId = userDetailsSession.getId();
        User currentUser = userService.getUserById(userId);
        model.addAttribute("friends", userService.getCurrentUserFriends(currentUser));
        return "friendsPage";
    }

    @PostMapping("/removeFriendship")
    public String removeFriendship(@ModelAttribute(value = "userId") Integer userId, Model model) {

        User currentUser = userService.getUserById(userDetailsSession.getId());
        User friend = userService.getUserById(userId);
        userService.removeFriendship(currentUser, friend);
        return "redirect:/friends";
    }


}
