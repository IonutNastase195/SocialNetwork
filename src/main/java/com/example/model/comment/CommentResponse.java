package com.example.model.comment;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentResponse {
    private Long id;
    private Long userId;
    private Long postId;
    private String content;

}
