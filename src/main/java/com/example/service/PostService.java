package com.example.service;


import com.example.entity.Post;
import com.example.entity.User;
import com.example.exception.BusinessException;
import com.example.mapper.PostMapper;
import com.example.model.post.PostResponse;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final PostMapper postMapper;

    public List<PostResponse> getAllPosts(Integer userId) {
        User user = userService.getUserById(userId);
        List<Post> posts = postRepository.findAllByUserIsIn(user.getFriends());
        posts.addAll(postRepository.findAllByUserIsIn(user.getFriendOf()));
        posts.addAll(postRepository.findAllByUser(user));
        posts.sort(Post::compareTo);

        List<PostResponse> response = new ArrayList<>();
        for (Post post : posts) {
            PostResponse postResponse = PostResponse.builder()
                    .text(post.getText())
                    .date(post.getCreatedAt())
                    .user(post.getUser())
                    .build();
            response.add(postResponse);
        }

        return response;
    }

    public PostResponse getPostById(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Post not found!")
        );
        return postMapper.map(post);
    }

    public void deletePost(Integer id) {
        Post postToDelete = postRepository.findById(id).orElseThrow(() ->
                new BusinessException("The post that you want to delete does not exist!"));
        postRepository.deleteById(postToDelete.getId());
    }

    public void createPost(String post, Integer userId) {
        User user = userService.getUserById(userId);
        Post newPost = Post.builder()
                .text(post)
                .likes(0)
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();
        postRepository.save(newPost);
    }


}


