package com.example.service;

import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.exception.BusinessException;
import com.example.mapper.CommentMapper;
import com.example.model.comment.CommentRequest;
import com.example.model.comment.CommentResponse;
import com.example.model.comment.CommentUpdate;
import com.example.repository.CommentRepository;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<CommentResponse> getAllComments() {
        return commentMapper.toResponse(commentRepository.findAll());
    }

    public CommentResponse getCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Comment not found!")
        );
        return commentMapper.toResponse(comment);
    }

    public CommentResponse createComment(CommentRequest commentRequest) {
        User user = userRepository.findById(commentRequest.getUserId()).orElseThrow(
                () -> new BusinessException("User not found!")
        );
        Post post = postRepository.findById(commentRequest.getPostId()).orElseThrow(
                () -> new BusinessException("Post not found!")
        );
        Comment comment = commentMapper.toEntity(commentRequest);
        comment.setUser(user);
        comment.setPost(post);
        Comment commentSaved = commentRepository.save(comment);
        return commentMapper.toResponse(commentSaved);
    }

    public void updateCommentById(Long id, CommentUpdate commentUpdate) {
        Comment commentToUpdate = commentRepository.findById(id).orElseThrow(
                () -> new BusinessException("The comment with the inserted id does not exist!")
        );
        commentToUpdate.setText(commentUpdate.getContent());
    }

    public void deleteComment(Long id) {
        Comment commentToDelete = commentRepository.findById(id).orElseThrow(() ->
                new BusinessException("The comment that you want to delete does not exist!"));
        commentRepository.deleteById(commentToDelete.getId());
    }
}
