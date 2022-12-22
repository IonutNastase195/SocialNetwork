package com.example.model.friendship;

import lombok.*;

@Data
public class FriendshipRequest {
    private Integer user1Id;
    private Integer user2Id;
    private String status;
}
