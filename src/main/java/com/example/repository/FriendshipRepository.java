package com.example.repository;

import com.example.entity.Friendship;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {
    List<Friendship> findByUser1OrUser2(User currentUser, User currentUser1);

    List<Friendship> findByUser1IdOrUser2Id(Integer id, Integer id1);


}

