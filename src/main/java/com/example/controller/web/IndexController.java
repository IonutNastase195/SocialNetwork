package com.example.controller.web;

import com.example.model.afterLogIn.UserDetailsSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequiredArgsConstructor
public class IndexController {
    private final UserDetailsSession userDetailsSession;


    @GetMapping("/")
    public String goToIndexPage(Model model) {
        model.addAttribute("userDetailsSession", userDetailsSession);
        return "index";
    }

    @GetMapping("/index")
    public String home(Model model) {
        model.addAttribute("userDetailsSession", userDetailsSession);
        return "index";
    }
}
