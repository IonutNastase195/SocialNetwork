package com.example.controller.web;

import com.example.entity.Friendship;
import com.example.model.friendship.FriendshipRequest;
import com.example.model.friendship.FriendshipResponse;
import com.example.model.friendship.FriendshipUpdate;
import com.example.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/4")
public class FriendshipWebController {
    private final FriendshipService friendshipService;

    @GetMapping("/all")
    public String getAllFriendships(Model model) {
        model.addAttribute("friendships", friendshipService.getAllFriendships());
        return "allFriendshipsPage";
    }

    @GetMapping("/create")
    public String goToCreateFriendshipPage() {
        return "createFriendshipPage";
    }

    @PostMapping("/create")
    public String createFriendship(@ModelAttribute(value = "friendshipRequest") FriendshipRequest friendshipRequest, Model model) {
        FriendshipResponse friendship = friendshipService.createFriendship(friendshipRequest);
        model.addAttribute("friendship", friendship);
        return "friendshipCreatedPage";
    }

    @GetMapping("/update")
    public String goToUpdateFriendshipPage(@ModelAttribute(value = "friendshipId") int friendshipId, Model model) {
        model.addAttribute("friendshipId", friendshipId);
        return "updateFriendshipPage";
    }

    @PostMapping("/update")
    public String updateFriendship(@ModelAttribute(value = "friendshipUpdate") FriendshipUpdate friendshipUpdate, Model model) {
        Friendship friendship = friendshipService.updateFriendshipById(friendshipUpdate.getId(), friendshipUpdate);
        model.addAttribute("friendship", friendship);
        return "friendshipUpdatedPage";
    }

    @PostMapping("/delete")
    public String deleteFriendship(@ModelAttribute(value = "friendshipId") int friendshipId, Model model) {
        friendshipService.deleteFriendship(friendshipId);
        model.addAttribute("friendships", friendshipService.getAllFriendships());
        return "allFriendshipsPage";
    }


}