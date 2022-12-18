package com.example.service;

import com.example.exception.BusinessException;
import com.example.model.user.UserRequest;
import com.example.model.user.UserResponse;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.model.user.UserUpdateRequest;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    public List<UserResponse> allUsers() {
        return userMapper.map(userRepository.findAll());
    }

    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found!")
        );
        return userMapper.map(user);
    }

    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.map(userRequest);
        if(isValidPassword(user.getPassword()) && isValidEmail(user.getEmail())) {
            User userSaved = userRepository.save(user);
            return userMapper.map(userSaved);
        }else{
            throw new BusinessException("The password should be 8 digits long and contain( a number, letter and a special char) or Email not valid.");
        }
    }


    public void updateUserById(UserUpdateRequest userUpdateRequest) {
        if(isValidPassword(userUpdateRequest.getPassword()) && isValidEmail(userUpdateRequest.getEmail())) {
        User userToUpdate = userRepository.findById(userUpdateRequest.getUserId()).orElseThrow(
                () -> new BusinessException(
                        "The user with the inserted id does not exist!"
                )
        );
        userToUpdate.setName(userUpdateRequest.getName());
        userToUpdate.setEmail(userUpdateRequest.getEmail());
        userToUpdate.setPassword(userUpdateRequest.getPassword());
        }else{
            throw new BusinessException("The password should be 8 digits long and contain( a number, letter and a special char) or Email not valid.");
        }
    }

    public void deleteUser(Integer id) {
        User userToDelete = userRepository.findById(id).orElseThrow(() ->
                new BusinessException("The user that you want to delete does not exist!"));
        userRepository.deleteById(userToDelete.getUserId());
    }

    public static boolean isValidPassword(String password) {
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

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
