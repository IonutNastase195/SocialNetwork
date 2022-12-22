package com.example.model.post;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Data
public class PostRequest {
    private String text;
    private String media;
    private Integer likes;
    private Integer comments;
    private Integer shares;
}
