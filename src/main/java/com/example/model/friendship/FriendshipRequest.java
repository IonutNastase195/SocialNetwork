package com.example.model.friendship;

import lombok.*;

@Data
public class FriendshipRequest {
    private Long user1Id;
    private Long user2Id;
    private String status;
}
