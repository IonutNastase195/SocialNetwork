package com.example.controller.web;

import com.example.model.afterLogIn.UserDetailsSession;
import com.example.model.user.UserRequest;
import com.example.model.user.UserRequestToLogIn;
import com.example.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class LogInAndOutController {
    private final UserService userService;
    private final UserDetailsSession userDetailsSession;

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
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "loginPage";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        try {
            userDetailsSession.clear();
            return "loginPage";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/logout")
    public String logoutPage() {
        userDetailsSession.clear();
        return "loginPage";
    }
}
