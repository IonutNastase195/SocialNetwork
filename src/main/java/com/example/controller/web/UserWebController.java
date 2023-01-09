package com.example.controller.web;

import com.example.model.postLogIn.UserDetailsSession;
import com.example.model.user.UserRequest;
import com.example.model.user.UserRequestToLogIn;
import com.example.model.user.UserUpdate;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


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


    @GetMapping("/loginPage")
    public String goToLogInPage(Model model) {
        model.addAttribute("userLoginRequest", new UserRequest());
        return "loginPage";
    }

    @PostMapping("/loginPage")
    public String login(@ModelAttribute(value = "userLoginRequest") UserRequestToLogIn userRequestToLogIn, Model model) {
        try {
            userService.login(userRequestToLogIn);
            model.addAttribute("userDetailsSession", userDetailsSession);
            return "index";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "loginPage";
        }
    }

    @GetMapping("findAllUsers")
    public String goToAllUsersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "allUsersPage";
    }



    @GetMapping("/update-user-page")
    public String goToUpdateUserPage(@ModelAttribute(value = "id") Integer id, Model model) {
        model.addAttribute("userId", id);
        return "updateUserPage";
    }

    @PostMapping("update/{id}")
    public String updateUserById(@ModelAttribute(value = "updateUser") UserUpdate userUpdate,
                                 @ModelAttribute(value = "id") Integer id,
                                 Model model) {
        userService.updateUserById(id, userUpdate);
        model.addAttribute("user", userService.getUserById(id));
        return "userPage";
    }

    @PostMapping("delete/{id}")
    public String deleteUser(@ModelAttribute(value = "id") Integer id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("users", userService.getAllUsers());
        return "allUsersPage";
    }

//    @GetMapping("/myProfile")
//    public String goToMyProfile(Model model) {
//        model.addAttribute("userLoginRequest", new UserRequest());
//        return "myProfile";
//    }
//
//    @GetMapping("/friends")
//    public String goToFriends(Model model) {
//        model.addAttribute("userLoginRequest", new UserRequest());
//        return "friends";
//    }
//
//    @GetMapping("/messages")
//    public String goToMessages(Model model) {
//        model.addAttribute("userLoginRequest", new UserRequest());
//        return "messages";
//    }
//
//    @GetMapping("/groupsPage")
//    public String goToGroupsPage(Model model) {
//        model.addAttribute("userLoginRequest", new UserRequest());
//        return "groupsPage";
//    }
//
//    @GetMapping("/allEvents")
//    public String goToAllEvents(Model model) {
//        model.addAttribute("userLoginRequest", new UserRequest());
//        return "allEvents";
//    }
//
//    @GetMapping("/goToMyProfilePage")
//    public String goToUserPage(Integer id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "myProfile";
//    }
}
