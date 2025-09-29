package com.example.todolist.controller;


import com.example.todolist.model.User;
import com.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(User user) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        ((UserService) userService).registerUser(user);
        return "redirect:/login";
    }
}
