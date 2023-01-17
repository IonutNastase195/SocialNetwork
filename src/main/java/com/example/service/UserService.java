package com.example.service;

import com.example.entity.Friendship;
import com.example.entity.User;
import com.example.exception.BusinessException;
import com.example.mapper.UserMapper;
import com.example.model.afterLogIn.UserDetailsSession;
import com.example.model.user.UpdateProfileRequest;
import com.example.model.user.UserRequest;
import com.example.model.user.UserRequestToLogIn;
import com.example.model.user.UserResponse;
import com.example.repository.FriendshipRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
        User currentUser = userDetailsSession.getUser();
        List<User> allUsers = userRepository.findAll();
        List<UserResponse> response = new ArrayList<>();
        for (User user : allUsers) {
            boolean isFriend = false;
            if (user.getFriends().contains(currentUser.getId())) {
                isFriend = true;
            }
            UserResponse userResponse = UserResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .isFriend(isFriend)
                    .build();
            response.add(userResponse);
        }
        return response;
    }

    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        UserResponse userResponse = userMapper.map(user);
        userDetailsSession.setUser(user);
        return userResponse;
    }

    public void login(UserRequestToLogIn user) {
        UserResponse userResponse = getUserByEmail(user.getEmail());
        if (!userResponse.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        userDetailsSession.setId(userResponse.getId());
        userDetailsSession.setName(userResponse.getName());
        userDetailsSession.setEmail(userResponse.getEmail());
        userDetailsSession.setPassword(userResponse.getPassword());
    }

    public User getCurrentUserProfile() {
        User currentUser = userRepository.findById(userDetailsSession.getId()).orElse(null);
        if (currentUser == null) {
            throw new IllegalArgumentException("User not found");
        }
        return currentUser;
    }


    private UserResponse convertToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        return userResponse;
    }

    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.map(userRequest);
        return userMapper.map(userRepository.save(user));
    }

    public void addFriend(Integer currentUserId, UserRequest friendRequest) {
        User friend = userRepository.findByEmail(friendRequest.getEmail());
        if (friend == null) {
            throw new IllegalArgumentException("User not found");
        }
        User currentUser = userRepository.findById(currentUserId).orElse(null);
        friendshipService.addFriendship(currentUser.getId(), friend.getId());
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new BusinessException("The user with the inserted id does not exist!"));
    }


    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new BusinessException("User not found!")
        );
        userRepository.delete(user);
        userDetailsSession.clear();
    }

    public List<UserResponse> getCurrentUserFriends() {
        User currentUser = userRepository.findById(userDetailsSession.getUser().getId()).orElse(null);
        assert currentUser != null;
        List<Friendship> friendships = friendshipRepository.findByUser1IdOrUser2Id(currentUser.getId(), currentUser.getId());
        List<UserResponse> friends = new ArrayList<>();
        for (Friendship friendship : friendships) {
            if (friendship.getUser1().getId().equals(currentUser.getId())) {
                friends.add(userMapper.map(userRepository.findById(friendship.getUser2().getId()).orElse(null)));
            } else {
                friends.add(userMapper.map(userRepository.findById(friendship.getUser1().getId()).orElse(null)));
            }
        }
        return friends;
    }


    public void updateUserById(Integer id, UpdateProfileRequest updateProfileRequest) {
        User userToUpdate = userRepository.findById(id).orElseThrow(
                () -> new BusinessException("The user with the inserted id does not exist!")
        );
        userToUpdate.setName(updateProfileRequest.getName());
        userToUpdate.setEmail(updateProfileRequest.getEmail());
        userToUpdate.setPassword(updateProfileRequest.getPassword());
        userRepository.save(userToUpdate);
    }

    public UserResponse updateProfile(UpdateProfileRequest updateProfileRequest) {
        User user = userDetailsSession.getUser();
        user.setName(updateProfileRequest.getName());
        user.setEmail(updateProfileRequest.getEmail());
        user = userRepository.save(user);
        UserResponse userResponse = userMapper.map(user);
        userDetailsSession.setUser(user);
        return userResponse;
    }


    public void update(UserRequest userRequest) {
        User userToUpdate = userRepository.findById(userRequest.getId()).orElseThrow(
                () -> new BusinessException(
                        String.format("The user with id: %s not exist", userRequest.getId())
                )
        );
        userToUpdate.setId(userRequest.getId());
        userToUpdate.setName(userRequest.getName());
        userToUpdate.setEmail(userRequest.getEmail());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


}



