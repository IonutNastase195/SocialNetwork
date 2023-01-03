package com.example.controller.web;

import com.example.entity.User;
import com.example.model.user.UserRequest;
import com.example.model.user.UserUpdate;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/1")
public class UserWebController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("findAllUsers")
    public String goToAllUsersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "allUsersPage";
    }

    @GetMapping("/goToUserPage")
    public String goToUserPage(@ModelAttribute(value = "id") Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userPage";
    }

    @PostMapping("createUserPage")
    public String createAccount(@RequestParam("name") String name,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password,
                                Model model) {
        try {
            userService.createUser(UserRequest.builder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .build());
            model.addAttribute("message", "Account created successfully!");
            return "redirect:/success.html";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/error.html";
        }
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

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        User user = userService.findByEmailAndPassword(email, password);
        if (user != null) {
            return "redirect:/index.html";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "redirect:/login.html";
        }
    }
}
