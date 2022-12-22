package com.example.model.post;

import com.example.model.user.UserResponse;
import lombok.*;

import java.time.LocalDateTime;


@Data
public class PostResponse {
    private Long id;
    private String text;
    private String media;
    private Integer likes;
    private Integer comments;
    private Integer shares;
    private LocalDateTime createdAt;
    private UserResponse user;

}