package com.example.Car_Rental_System.controller;

import com.example.Car_Rental_System.entity.User;
import com.example.Car_Rental_System.service.RentalRequestService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RentalRequestService rentalRequestService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        if(session.getAttribute("loggedInUser") == null) return "redirect:/";
        return "user-dashboard";
    }

    @PostMapping("/request")
    public String requestCar(Long carId, int days, HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");
        if(user == null) return "redirect:/";

        rentalRequestService.requestCar(carId, user.getId(), days);

        return "redirect:/user/dashboard";
    }
    @GetMapping("/myrequests")
    public String myRequests(HttpSession session) {
        if(session.getAttribute("loggedInUser") == null) return "redirect:/";
        return "user-requests";
    }
}