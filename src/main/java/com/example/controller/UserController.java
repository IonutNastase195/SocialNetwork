package com.example.controller;

import com.example.mapper.UserMapper;
import com.example.model.user.UserRequest;
import com.example.model.user.UserResponse;
import com.example.model.user.UserUpdate;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("findAll")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/find/{id}")
    public UserResponse getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("create")
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping("update/{id}")
    public void updateUserById(@PathVariable Integer id, @RequestBody UserUpdate userUpdate) {
        userService.updateUserById(id, userUpdate);
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
