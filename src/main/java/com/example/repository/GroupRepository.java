package com.example.repository;

import com.example.entity.Group;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GroupRepository extends JpaRepository<Group, Integer> {
    List<Group> findByMembersContaining(User currentUser);
}
