package com.example.controller.web;

import com.example.entity.Friendship;
import com.example.entity.User;
import com.example.model.afterLogIn.UserDetailsSession;
import com.example.model.friendship.FriendshipResponse;
import com.example.model.user.UserRequest;
import com.example.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FriendshipWebController {
    private final FriendshipService friendshipService;
    private final UserService userService;
    private final UserDetailsSession userDetailsSession;

   // @PostMapping("/addFriendship")
   // public String addFriend(@ModelAttribute User user, UserDetailsSession session) {
   //     User currentUser = session.getUser();
   //     Friendship friendship = new Friendship();
   //     friendship.setUser1(currentUser);
   //     friendship.setUser2(user);
   //     friendshipService.save(friendship);
   //     return "redirect:/allUsersPage";
   // }
//
   // @GetMapping("/allUsersPage/addFriendship")
   // public String displayAllUsers(Model model, UserDetailsSession session) {
   //     User currentUser = session.getUser();
   //     List<User> allUsers = userService.findAll();
   //     allUsers.remove(currentUser);
   //     model.addAttribute("currentFriends", friendshipService.findByUser1OrUser2(currentUser, currentUser));
   //     model.addAttribute("allUsers", allUsers);
   //     return "allUsersPage";
   // }


    @GetMapping("/friends")
    public String goToFriendsPage(Model model) {
        model.addAttribute("friends", friendshipService.getCurrentUserFriends());
        return "friendsPage";
    }

    @PostMapping("/friends/remove")
    public String removeFriend(@ModelAttribute(value = "friend") UserRequest friend, Model model) {
        try {
            friendshipService.removeFriendship(userService.getCurrentUserProfile(), friend);
            return "redirect:/friends";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "friendsPage";
        }
    }

    @GetMapping("/all")
    public List<FriendshipResponse> getAllFriendships() {
        return friendshipService.getAllFriendships();
    }

    @GetMapping("/currentUser")
    public List<FriendshipResponse> getAllFriendshipsForCurrentUser() {
        return friendshipService.getAllFriendships();
    }

    @PutMapping("/{id}/accept")
    public void acceptFriendshipRequest(@PathVariable Integer id) {
        friendshipService.acceptFriendship(id, id);
    }

    @PostMapping("/removeFriendship")
    public String removeFriendship(@ModelAttribute(value = "friend") UserRequest friend, Model model) {
        try {
            friendshipService.removeFriendship(userDetailsSession.getUser(), friend);
            model.addAttribute("friends", friendshipService.getCurrentUserFriends());
            return "redirect:/friends";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "friendsPage";
        }

    }

}
