package com.example.repository;

import com.example.entity.Post;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    Optional<User> findById(Integer id);
    User save(User user);
    void deleteById(Integer id);



}
