package com.example.service;

import com.example.entity.Friendship;
import com.example.repository.FriendshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;

    public List<Friendship> getAllFriendships() {
        return friendshipRepository.findAll();
    }

    public Optional<Friendship> getFriendshipById(Integer id) {
        return friendshipRepository.findById(id);
    }

    public Friendship addFriendship(Friendship friendship) {
        return friendshipRepository.save(friendship);
    }

    public Friendship updateFriendship(Integer friendshipId, Friendship friendship) {
        return friendshipRepository.save(friendship);
    }

    public void deleteFriendship(Integer id) {
        friendshipRepository.deleteById(id);
    }
}

