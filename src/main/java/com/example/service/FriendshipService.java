package com.example.service;

import com.example.entity.Friendship;
import com.example.entity.User;
import com.example.exception.BusinessException;
import com.example.mapper.FriendshipMapper;
import com.example.model.afterLogIn.UserDetailsSession;
import com.example.model.friendship.FriendshipRequest;
import com.example.model.friendship.FriendshipResponse;
import com.example.model.friendship.FriendshipUpdate;
import com.example.model.user.UserRequest;
import com.example.repository.FriendshipRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final FriendshipMapper friendshipMapper;
    private final UserRepository userRepository;
    private final UserDetailsSession userDetailsSession;

    public List<FriendshipResponse> getAllFriendships() {
        return friendshipMapper.toResponse(friendshipRepository.findAll());
    }

    public FriendshipResponse getFriendshipById(Integer id) {
        Friendship friendship = friendshipRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Friendship not found!")
        );
        return friendshipMapper.toResponse(friendship);
    }

    public FriendshipResponse createFriendship(FriendshipRequest friendshipRequest) {
        User user1 = userRepository.findById(friendshipRequest.getUser1Id()).orElseThrow(() -> new BusinessException("User1 not found!"));
        User user2 = userRepository.findById(friendshipRequest.getUser2Id()).orElseThrow(() -> new BusinessException("User2 not found!"));

        Friendship friendship = friendshipMapper.toEntity(friendshipRequest);
        friendship.setUser1(user1);
        friendship.setUser2(user2);
        Friendship friendshipSaved = friendshipRepository.save(friendship);
        return friendshipMapper.toResponse(friendshipSaved);
    }

    public Friendship updateFriendshipById(Integer id, FriendshipUpdate friendshipUpdate) {
        Friendship friendshipToUpdate = friendshipRepository.findById(id).orElseThrow(
                () -> new BusinessException("The friendship with the inserted id does not exist!")
        );
        friendshipToUpdate.setStatus(friendshipUpdate.getStatus());
        return friendshipToUpdate;
    }

    public void deleteFriendship(Integer id) {
        Friendship friendshipToDelete = friendshipRepository.findById(id).orElseThrow(() ->
                new BusinessException("The friendship that you want to delete does not exist!"));
        friendshipRepository.deleteById(friendshipToDelete.getId());
    }

    public List<User> getCurrentUserFriends() {
        User currentUser = userDetailsSession.getUser();
        List<Friendship> friendships = friendshipRepository.findByUser1OrUser2(currentUser, currentUser);
        List<User> friends = new ArrayList<>();
        for (Friendship friendship : friendships) {
            if (friendship.getUser1().equals(currentUser)) {
                friends.add(friendship.getUser2());
            } else {
                friends.add(friendship.getUser1());
            }
        }
        return friends;
    }

    public void addFriendship(Integer currentUserId, Integer id) {
    }

    public void acceptFriendship(Integer id, Integer id1) {
    }

    public void removeFriendship(User user, UserRequest friend) {
    }
}