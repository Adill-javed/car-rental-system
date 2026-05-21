package com.example.Car_Rental_System.controller;

import com.example.Car_Rental_System.entity.Car;
import com.example.Car_Rental_System.entity.User;
import com.example.Car_Rental_System.service.CarService;
import com.example.Car_Rental_System.service.RentalRequestService;
import jakarta.servlet.http.HttpSession;
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

    private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        return user != null && "ADMIN".equals(user.getRole());
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        if(!isAdmin(session)) return "redirect:/";
        return "admin-dashboard";
    }

    @GetMapping("/addcar")
    public String addCarPage(HttpSession session) {
        if(!isAdmin(session)) return "redirect:/";
        return "add-car";
    }

    @PostMapping("/savecar")
    public String saveCar(Car car, HttpSession session) {
        if(!isAdmin(session)) return "redirect:/";
        carService.saveCar(car);
        return "redirect:/admin/dashboard";
    }
    
    @PostMapping("/deletecar/{id}")
    public String deleteCar(@PathVariable Long id, HttpSession session) {
        if(!isAdmin(session)) return "redirect:/";
        carService.deleteCar(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/requests")
    public String viewRequests(HttpSession session) {
        if(!isAdmin(session)) return "redirect:/";
        return "requests";
    }

    @GetMapping("/approve/{id}")
    public String approveRequest(@PathVariable Long id, HttpSession session) {
        if(!isAdmin(session)) return "redirect:/";
        rentalRequestService.approveRequest(id);
        return "redirect:/admin/requests";
    }
    
    @GetMapping("/reject/{id}")
    public String rejectRequest(@PathVariable Long id, HttpSession session) {
        if(!isAdmin(session)) return "redirect:/";
        rentalRequestService.rejectRequest(id);
        return "redirect:/admin/requests";
    }
    
    @GetMapping("/return/{id}")
    public String returnCar(@PathVariable Long id, HttpSession session) {
        if(!isAdmin(session)) return "redirect:/";
        rentalRequestService.returnCar(id);
        return "redirect:/admin/requests";
    }
}