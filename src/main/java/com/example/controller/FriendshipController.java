package com.example.controller;

import com.example.entity.Friendship;
import com.example.mapper.FriendshipMapper;
import com.example.model.friendship.FriendshipRequest;
import com.example.model.friendship.FriendshipResponse;
import com.example.model.friendship.FriendshipUpdate;
import com.example.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/friendships")
@RestController
@RequiredArgsConstructor
public class FriendshipController {

    private final FriendshipService friendshipService;

    @GetMapping()
    public List<FriendshipResponse> getAllFriendships() {
        return friendshipService.getAllFriendships();
    }

    @GetMapping("/friendships/{id}")
    public FriendshipResponse getFriendshipById(@PathVariable Integer id) {
        return friendshipService.getFriendshipById(id);
    }

    @PostMapping("/friendships")
    public FriendshipResponse createFriendship(@RequestBody FriendshipRequest friendshipRequest) {
        return friendshipService.createFriendship(friendshipRequest);
    }

    @PutMapping("/friendships/{id}")
    public void updateFriendshipById(@PathVariable Integer id, @RequestBody FriendshipUpdate friendshipUpdate) {
        friendshipService.updateFriendshipById(id, friendshipUpdate);
    }

    @DeleteMapping("/friendships/{id}")
    public void deleteFriendship(@PathVariable Integer id) {
        friendshipService.deleteFriendship(id);
    }
}
