package com.example.model.post;

import com.example.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequest {
    private Integer postId;
    private User user;
    private String text;
}
