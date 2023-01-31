package com.example.repository;

import com.example.entity.Post;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAllByUserIsIn(Set<User> users);

    List<Post> findAllByUser(User user);
}
