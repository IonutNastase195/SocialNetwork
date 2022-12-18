package com.example.model.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {
    private Integer postId;
    @NotBlank
    private String text;
}
