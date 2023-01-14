package com.example.controller.web;

import com.example.model.afterLogIn.UserDetailsSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class LogOutController {
    private final UserDetailsSession userDetailsSession;

    @GetMapping("/logout")
    public String logout() {
        userDetailsSession.clear();
        return "redirect:/";
    }
}
