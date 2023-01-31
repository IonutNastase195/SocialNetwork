package com.example.model.post;

import com.example.entity.User;
import com.example.model.user.UserResponse;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Builder
public class PostResponse {
    private User user;
    private String text;
    private LocalDateTime date;

}