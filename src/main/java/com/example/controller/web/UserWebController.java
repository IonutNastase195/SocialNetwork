package com.example.controller.web;

import com.example.entity.User;
import com.example.model.afterLogIn.UserDetailsSession;
import com.example.model.post.PostRequest;
import com.example.model.user.UserRequest;
import com.example.model.user.UpdateProfileRequest;
import com.example.model.user.UserResponse;
import com.example.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserWebController {

    private final UserService userService;
    private final UserDetailsSession userDetailsSession;

    @GetMapping("/createUserPage")
    public String goToCreateUserPage(Model model) {
        model.addAttribute("userFromWeb", new UserRequest());
        return "createUserPage";
    }

    @PostMapping("/createUserPage")
    public String createAccount(@ModelAttribute(value = "createUserRequest") UserRequest userRequest, Model model) {
        try {
            userService.createUser(userRequest);
            return "success";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/myProfile")
    public String goToMyProfilePage(Model model) {
        model.addAttribute("userProfile", userService.getCurrentUserProfile());
        return "myProfilePage";
    }

    @GetMapping("/updateProfile")
    public String goToUpdateProfilePage(Model model) {
        User user = userDetailsSession.getUser();
        model.addAttribute("userProfile", user);
        return "updateProfile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute(value = "updateProfileRequest") UpdateProfileRequest updateProfileRequest, Model model) {
        try {
            UserResponse userResponse = userService.updateProfile(updateProfileRequest);
            model.addAttribute("user", userResponse);
            return "profileUpdated";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/allUsersPage")
    public String goToAllUsersPage(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "allUsersPage";
    }

    @PostMapping("/allUsersPage")
    public String updateUser(@ModelAttribute(value = "updateUserRequest") UserRequest request,
                             Model model) {
        UserRequest userRequest = UserRequest.builder()
                .id(request.getId())
                .name(request.getName())
                .email(request.getEmail())
                .build();
        userService.update(userRequest);

        model.addAttribute("allUsers", userService.getAllUsers());
        return "allUsersPage";
    }


}
