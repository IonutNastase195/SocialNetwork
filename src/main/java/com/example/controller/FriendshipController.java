package com.example.controller;

import com.example.entity.Friendship;
import com.example.service.FriendshipService;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/friendships")
public class FriendshipController {

    private final FriendshipService friendshipService;

    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @PostMapping
    public Friendship createFriendship(@RequestBody Friendship friendship) {
        return friendshipService.addFriendship(friendship);
    }

    @DeleteMapping("/{friendshipId}")
    public void deleteFriendship(@PathVariable Integer friendshipId) {
        friendshipService.deleteFriendship(friendshipId);
    }

    @GetMapping("/{friendshipId}")
    public Optional<Friendship> getFriendshipById(@PathVariable Integer friendshipId) {
        return friendshipService.getFriendshipById(friendshipId);
    }

    @PutMapping("/{friendshipId}")
    public Friendship updateFriendship(@PathVariable Integer friendshipId, @RequestBody Friendship friendship) {
        return friendshipService.updateFriendship(friendshipId, friendship);
    }

}
