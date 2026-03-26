package com.realnest.realnestbackend.controller;

import com.realnest.realnestbackend.model.User;
import com.realnest.realnestbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService service;

    // ✅ SHOW REGISTER PAGE
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // ✅ REGISTER USER
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        service.save(user);
        return "redirect:/login";
    }

    // ✅ SHOW LOGIN PAGE
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // ✅ LOGIN USER
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            Model model) {

        User user = service.login(email, password);

        if (user != null) {
            return "redirect:/properties-ui";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}