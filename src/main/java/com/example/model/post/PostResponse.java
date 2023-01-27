package com.example.model.post;

import com.example.model.user.UserResponse;
import lombok.*;

import java.time.LocalDateTime;


@Data
public class PostResponse {
    private Integer id;
    private String text;

}