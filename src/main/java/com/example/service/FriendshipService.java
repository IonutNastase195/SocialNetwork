package com.example.service;

import com.example.entity.Connection;
import com.example.entity.Friendship;
import com.example.entity.User;
import com.example.exception.BusinessException;
import com.example.mapper.FriendshipMapper;
import com.example.model.friendship.FriendshipRequest;
import com.example.model.friendship.FriendshipResponse;
import com.example.model.friendship.FriendshipUpdate;
import com.example.repository.FriendshipRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final FriendshipMapper friendshipMapper;
    private final UserRepository userRepository;

    public List<FriendshipResponse> getAllFriendships() {
        return friendshipMapper.toResponse(friendshipRepository.findAll());
    }

    public FriendshipResponse getFriendshipById(Long id) {
        Friendship friendship = friendshipRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Friendship not found!")
        );
        return friendshipMapper.toResponse(friendship);
    }

    public FriendshipResponse createFriendship(FriendshipRequest friendshipRequest) {
        User user1 = userRepository.findById(friendshipRequest.getUser1Id()).orElseThrow(
                () -> new BusinessException("User1 not found!")
        );
        User user2 = userRepository.findById(friendshipRequest.getUser2Id()).orElseThrow(
                () -> new BusinessException("User2 not found!")
        );
        Friendship friendship = friendshipMapper.toEntity(friendshipRequest);
        friendship.setUser1(user1);
        friendship.setUser2(user2);
        Friendship friendshipSaved = friendshipRepository.save(friendship);
        return friendshipMapper.toResponse(friendshipSaved);
    }

    public void updateFriendshipById(Long id, FriendshipUpdate friendshipUpdate) {
        Friendship friendshipToUpdate = friendshipRepository.findById(id).orElseThrow(
                () -> new BusinessException("The friendship with the inserted id does not exist!")
        );
        friendshipToUpdate.setStatus(friendshipUpdate.getStatus());
    }

    public void deleteFriendship(Long id) {
        Friendship friendshipToDelete = friendshipRepository.findById(id).orElseThrow(() ->
                new BusinessException("The friendship that you want to delete does not exist!"));
        friendshipRepository.deleteById(friendshipToDelete.getId());
    }
}