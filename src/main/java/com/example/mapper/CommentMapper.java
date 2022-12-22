package com.example.mapper;

import com.example.entity.Comment;
import com.example.model.comment.CommentRequest;
import com.example.model.comment.CommentResponse;
import com.example.model.comment.CommentUpdate;
import org.mapstruct.Mapper;


import java.util.List;
@Mapper
public interface CommentMapper {
    Comment toEntity(CommentRequest request);
    CommentResponse toResponse(Comment entity);
    CommentUpdate toUpdate(Comment entity);
    List<CommentResponse> toResponse(List<Comment> all);
}