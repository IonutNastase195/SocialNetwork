package com.example.model.comment;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentRequest {
    private Long userId;
    private Long postId;
    private String content;
}
