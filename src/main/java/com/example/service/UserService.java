package com.example.service;

import com.example.entity.User;
import com.example.exception.BusinessException;
import com.example.mapper.UserMapper;
import com.example.model.user.UserRequest;
import com.example.model.user.UserResponse;
import com.example.model.user.UserUpdate;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private Validator validator;

    public List<UserResponse> getAllUsers() {
        return userMapper.toResponse(userRepository.findAll());
    }

    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found!")
        );
        return userMapper.toResponse(user);
    }

    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        return userMapper.toResponse(userRepository.save(user));
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

    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
