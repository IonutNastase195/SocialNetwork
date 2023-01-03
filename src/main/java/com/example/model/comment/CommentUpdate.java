package com.example.model.comment;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentUpdate {
    private Integer id;
    private String content;
}
