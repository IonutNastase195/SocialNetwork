package com.example.model.post;

import com.example.entity.Comment;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
public class PostRequest {
    private String text;
    private Integer likes;

}
