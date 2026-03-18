package com.example.Car_Rental_System.controller;

import com.example.Car_Rental_System.entity.User;
import com.example.Car_Rental_System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String email, String password) {

        User user = userService.login(email, password);

        if (user != null) {

            if (user.getRole().equals("ADMIN")) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/user/dashboard";
            }
        }

        return "redirect:/";
    }
}