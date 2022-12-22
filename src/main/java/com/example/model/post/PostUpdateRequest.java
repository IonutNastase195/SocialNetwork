package com.example.model.post;

import com.example.entity.User;
import lombok.*;

@Data
public class PostUpdateRequest {
    private Integer id;
    private String text;
    private String media;
    private Integer likes;
    private Integer comments;
    private Integer shares;
}
