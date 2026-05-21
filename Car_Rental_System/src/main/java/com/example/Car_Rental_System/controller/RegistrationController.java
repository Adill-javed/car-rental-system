package com.example.Car_Rental_System.controller;

import com.example.Car_Rental_System.entity.User;
import com.example.Car_Rental_System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        User savedUser = userService.register(user);
        if (savedUser == null) {
            model.addAttribute("error", "Email already exists. Try logging in.");
            return "register";
        }
        return "redirect:/?success=registered";
    }
}
