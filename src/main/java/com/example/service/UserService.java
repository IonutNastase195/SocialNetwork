package com.example.service;

import com.example.entity.Friendship;
import com.example.entity.User;
import com.example.exception.BusinessException;
import com.example.mapper.UserMapper;
import com.example.model.afterLogIn.UserDetailsSession;
import com.example.model.user.UserRequest;
import com.example.model.user.UserRequestToLogIn;
import com.example.model.user.UserResponse;
import com.example.model.user.UserUpdate;
import com.example.repository.FriendshipRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserDetailsSession userDetailsSession;
    private final FriendshipService friendshipService;
    private final FriendshipRepository friendshipRepository;



    public List<UserResponse> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserResponse> allUsersResponse = new ArrayList<>();
        for (User user : allUsers) {
            allUsersResponse.add(userMapper.toResponse(user));
        }
        return allUsersResponse;
    }

    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found!")
        );
        return userMapper.toResponse(user);
    }

    public void login(UserRequestToLogIn user) {
        UserResponse userResponse = getUserByEmail(user.getEmail());
        if (!userResponse.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        userDetailsSession.setName(user.getName());
        userDetailsSession.setPassword(user.getPassword());
    }

    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return convertToUserResponse(user);
    }

    private UserResponse convertToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        return userResponse;
    }


    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        return userMapper.toResponse(userRepository.save(user));
    }

    public User getCurrentUserProfile() {
        User currentUser = userRepository.findByEmail(userDetailsSession.getEmail());
        if (currentUser == null) {
            throw new IllegalArgumentException("User not found");
        }
        return currentUser;
    }


    public void updateUserById(Integer id, UserUpdate userUpdate) {
        User userToUpdate = userRepository.findById(id).orElseThrow(
                () -> new BusinessException("The user with the inserted id does not exist!")
        );
        userToUpdate.setName(userUpdate.getName());
        userToUpdate.setEmail(userUpdate.getEmail());
        userToUpdate.setPassword(userUpdate.getPassword());
    }

    public void deleteUser(Integer id) {
        {
            User userToDelete = userRepository.findById(id).orElseThrow(() ->
                    new BusinessException("The user that you want to delete does not exist!"));
            userRepository.deleteById(userToDelete.getId());
        }
    }

    public void addFriend(Integer currentUserId, UserRequest friendRequest) {
        User friend = userRepository.findByEmail(friendRequest.getEmail());
        if (friend == null) {
            throw new IllegalArgumentException("User not found");
        }
        User currentUser = userRepository.findById(currentUserId).orElse(null);
        friendshipService.addFriendship(currentUser.getId(), friend.getId());
    }

    public List<UserResponse> getCurrentUserFriends() {
        User currentUser = userRepository.findById(userDetailsSession.getUser().getId()).orElse(null);
        List<Friendship> friendships = friendshipRepository.findByUser1IdOrUser2Id(currentUser.getId(), currentUser.getId());
        List<UserResponse> friends = new ArrayList<>();
        for (Friendship friendship : friendships) {
            if (friendship.getUser1().getId().equals(currentUser.getId())) {
                friends.add(userMapper.toResponse(userRepository.findById(friendship.getUser2().getId()).orElse(null)));
            } else {
                friends.add(userMapper.toResponse(userRepository.findById(friendship.getUser1().getId()).orElse(null)));
            }
        }
        return friends;
    }
}

