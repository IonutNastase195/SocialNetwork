package com.example.service;

import com.example.entity.User;
import com.example.exception.BusinessException;
import com.example.mapper.UserMapper;
import com.example.model.afterLogIn.UserDetailsSession;
import com.example.model.user.UpdateProfileRequest;
import com.example.model.user.UserRequest;
import com.example.model.user.UserRequestToLogIn;
import com.example.model.user.UserResponse;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserDetailsSession userDetailsSession;

    public List<UserResponse> getAllUsers() {
        User currentUser = getUserById(userDetailsSession.getId());
        Set<User> friends = currentUser.getFriends();
        Set<User> friendsOf = currentUser.getFriendOf();
        List<User> allUsers = userRepository.findAll();
        allUsers.remove(currentUser);

        List<UserResponse> response = new ArrayList<>();
        for (User user : allUsers) {
            boolean isFriend = false;
            if (friends.contains(user) || friendsOf.contains(user)) {
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

    public void addFriend(Integer userId, Integer newFriendId) {
        Optional<User> newFriend = userRepository.findById(newFriendId);
        if (newFriend.isPresent()) {
            User user = userRepository.findById(userId).get();
            User friend = newFriend.get();
            user.getFriends().add(friend);

            userRepository.save(user);
        }
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

    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        UserResponse userResponse = userMapper.map(user);
        userDetailsSession.setUser(user);

        return userResponse;
    }

    public User getCurrentUserProfile() {
        User currentUser = userRepository.findById(userDetailsSession.getId()).orElse(null);
        if (currentUser == null) {
            throw new IllegalArgumentException("User not found");
        }
        return currentUser;
    }


    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.map(userRequest);
        return userMapper.map(userRepository.save(user));
    }


    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new BusinessException("The user with the inserted id does not exist!"));
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

    public Set<UserResponse> getCurrentUserFriends(User user) {
        Set<User> friends = user.getFriends();
        friends.addAll(user.getFriendOf());

        return userMapper.map(friends);
    }

    public void removeFriendship(User user, User friend) {
        Set<User> userFriends = user.getFriends();
        Set<User> friendsOfUser = user.getFriendOf();
        userFriends.remove(friend);
        friendsOfUser.remove(friend);

        userRepository.save(user);
    }

}



