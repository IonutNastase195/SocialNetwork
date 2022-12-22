package com.example.model.friendship;

import com.example.model.user.UserResponse;
import lombok.*;

@Data
public class FriendshipResponse {
    private Integer id;
    private UserResponse user1;
    private UserResponse user2;
    private String status;
}
