package com.example.model.comment;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentRequest {
    private Integer userId;
    private Integer postId;
    private String content;
}
