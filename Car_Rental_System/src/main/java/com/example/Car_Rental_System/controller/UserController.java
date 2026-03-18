package com.example.Car_Rental_System.controller;

import com.example.Car_Rental_System.service.RentalRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RentalRequestService rentalRequestService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "user-dashboard";
    }

    @PostMapping("/request")
    public String requestCar(Long carId, int days) {

        Long userId = 1L;   // demo user

        rentalRequestService.requestCar(carId, userId, days);

        return "redirect:/user/dashboard";
    }
    @GetMapping("/myrequests")
    public String myRequests() {
        return "user-requests";
    }
}