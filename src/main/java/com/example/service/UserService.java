package com.example.service;

import com.example.entity.User;
import com.example.exception.BusinessException;
import com.example.model.user.UserRequest;
import com.example.model.user.UserResponse;
import com.example.mapper.UserMapper;
import com.example.model.user.UserUpdate;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> getAllUsers() {
        return userMapper.toResponse(userRepository.findAll());
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found!")
        );
        return userMapper.toResponse(user);
    }

    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        User userSaved = userRepository.save(user);
        return userMapper.toResponse(userSaved);
    }

    public void updateUserById(Long id, UserUpdate userUpdate) {
        User userToUpdate = userRepository.findById(id).orElseThrow(
                () -> new BusinessException("The user with the inserted id does not exist!")
        );
        userToUpdate.setName(userUpdate.getName());
        userToUpdate.setEmail(userUpdate.getEmail());
        userToUpdate.setPassword(userUpdate.getPassword());
    }

    public void deleteUser(Long id) {
        {
            User userToDelete = userRepository.findById(id).orElseThrow(() ->
                    new BusinessException("The user that you want to delete does not exist!"));
            userRepository.deleteById(userToDelete.getId());
        }
    }
    public static boolean isValidPassword (@NotNull String password){
        if (password.length() < 8) {
            return false;
        }
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isWhitespace(c)) {
                hasSpecialChar = true;
            }
        }
        return hasLetter && hasDigit && hasSpecialChar;
    }

    public static boolean isValidEmail (String email){
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
