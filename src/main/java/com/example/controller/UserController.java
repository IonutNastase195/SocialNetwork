package com.example.controller;

import com.example.model.user.UserRequest;
import com.example.model.user.UserResponse;
import com.example.model.user.UserUpdateRequest;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("find/{userId}")
    public UserResponse getUserById(@PathVariable Integer userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/create")
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PatchMapping("update")
    public void updateUserById(@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        userService.updateUserById(userUpdateRequest);
    }

    @DeleteMapping("delete/{userId}")
    public void deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("find-all")
    public List<UserResponse> getAllUsers() {
        return userService.allUsers();
    }


}
