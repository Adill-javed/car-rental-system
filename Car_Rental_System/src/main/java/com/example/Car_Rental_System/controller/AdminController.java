package com.example.Car_Rental_System.controller;

import com.example.Car_Rental_System.entity.Car;
import com.example.Car_Rental_System.service.CarService;
import com.example.Car_Rental_System.service.RentalRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CarService carService;

    @Autowired
    private RentalRequestService rentalRequestService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/addcar")
    public String addCarPage() {
        return "add-car";
    }

    @PostMapping("/savecar")
    public String saveCar(Car car) {
        carService.saveCar(car);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/requests")
    public String viewRequests() {
        return "requests";
    }

    @GetMapping("/approve/{id}")
    public String approveRequest(@PathVariable Long id) {
        rentalRequestService.approveRequest(id);
        return "redirect:/admin/requests";
    }
}