package com.example.model.comment;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentResponse {
    private Integer id;
    private Integer userId;
    private Integer postId;
    private String content;

}
