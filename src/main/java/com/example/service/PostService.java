package com.example.service;


import com.example.entity.Post;
import com.example.entity.User;
import com.example.exception.BusinessException;
import com.example.mapper.PostMapper;
import com.example.model.post.PostRequest;
import com.example.model.post.PostResponse;
import com.example.model.post.PostUpdateRequest;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;

    public List<PostResponse> getAllPosts() {
        return postMapper.map(postRepository.findAll());
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Post not found!")
        );
        return postMapper.map(post);
    }

    public PostResponse createPost(Long userId, PostRequest postRequest) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BusinessException("User not found!")
        );
        Post post = postMapper.map(postRequest);
        post.setUser(user);
        Post postSaved = postRepository.save(post);
        return postMapper.map(postSaved);
    }

    public void updatePostById(PostUpdateRequest postUpdateRequest) {
        Post postToUpdate = postRepository.findById(postUpdateRequest.getId()).orElseThrow(
                () -> new BusinessException(
                        "The post with the inserted id does not exist!"
                )
        );
        postToUpdate.setText(postUpdateRequest.getText());
        postToUpdate.setMedia(postUpdateRequest.getMedia());
        postToUpdate.setLikes(postUpdateRequest.getLikes());
        postToUpdate.setComments(postUpdateRequest.getComments());
        postToUpdate.setShares(postUpdateRequest.getShares());
    }

    public void deletePost(Long id) {
        Post postToDelete = postRepository.findById(id).orElseThrow(() ->
                new BusinessException("The post that you want to delete does not exist!"));
        postRepository.deleteById(postToDelete.getId());
    }
}


