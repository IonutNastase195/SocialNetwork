package com.example.service;

import com.example.entity.Post;
import com.example.exception.BusinessException;
import com.example.mapper.PostMapper;
import com.example.model.post.PostRequest;
import com.example.model.post.PostResponse;
import com.example.model.post.PostUpdateRequest;
import com.example.repository.PostRepository;
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

    public List<PostResponse> allPosts() {
        return postMapper.map(postRepository.findAll());
    }

    public PostResponse getPostById(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Post not found!")
        );
        return postMapper.map(post);
    }

    public PostResponse createPost(PostRequest postRequest) {
        Post post = postMapper.map(postRequest);
        Post postSaved = postRepository.save(post);
        return postMapper.map(postSaved);
    }

    public void updatePostById(PostUpdateRequest postUpdateRequest) {
        Post postToUpdate = postRepository.findById(postUpdateRequest.getPostId()).orElseThrow(
                () -> new BusinessException(
                        "The post with the inserted id does not exist!"
                )
        );
        postToUpdate.setText(postUpdateRequest.getText());
        postToUpdate.setUser(postUpdateRequest.getUser());
    }

    public void deletePost(Integer id) {
        Post postToDelete = postRepository.findById(id).orElseThrow(() ->
                new BusinessException("The post that you want to delete does not exist!"));
        postRepository.deleteById(postToDelete.getPostId());
    }
}

