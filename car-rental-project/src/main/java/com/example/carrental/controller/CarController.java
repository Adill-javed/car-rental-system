package com.example.carrental.controller;

import com.example.carrental.model.Car;
import com.example.carrental.repo.CarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarRepository repo;

    public CarController(CarRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/cars/available")
    public List<Car> getAvailableCars() {
        return repo.findAvailableCars();
    }

    @GetMapping("/cars/{id}/availability")
    public ResponseEntity<?> checkAvailability(@PathVariable int id) {
        Boolean available = repo.isAvailable(id);
        if (available == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of("id", id, "available", available));
    }

//    @GetMapping("/cars/{id}/estimate")
//    public ResponseEntity<?> estimate(@PathVariable int id, @RequestParam int days) {
//        Double price = repo.getPricePerDay(id);
//        if (price == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(Map.of("id", id, "days", days, "amount", price * days));
//    }
    @GetMapping("/cars/{id}/estimate")
    public ResponseEntity<?> estimate(@PathVariable int id, @RequestParam int days) {
        Double price = repo.getPricePerDay(id);
        if (price == null) return ResponseEntity.notFound().build();

        double discount = 0;
        if (days >= 15) discount = 0.15;
        else if (days >= 8) discount = 0.10;
        else if (days >= 5) discount = 0.05;

        double total = price * days * (1 - discount);

        return ResponseEntity.ok(Map.of(
                "id", id,
                "days", days,
                "basePricePerDay", price,
                "discountPercent", discount * 100,
                "finalAmount", total
        ));
    }


//    @PostMapping("/rent")
//    public ResponseEntity<?> rent(@RequestBody Map<String, Integer> body) {
//        Integer carId = body.get("carId");
//        Integer days = body.get("days");
//        if (carId == null || days == null) return ResponseEntity.badRequest().body("carId and days required");
//
//        Boolean avail = repo.isAvailable(carId);
//        if (avail == null) return ResponseEntity.notFound().build();
//        if (!avail) return ResponseEntity.status(409).body("Car not available");
//
//        Double pricePerDay = repo.getPricePerDay(carId);
//        double total = pricePerDay * days;
//        boolean success = repo.rentCar(carId);
//        if (success) {
//            return ResponseEntity.ok(Map.of("message", "Car rented", "total", total));
//        } else {
//            return ResponseEntity.status(500).body("Failed to rent car");
//        }
//    }
    @PostMapping("/rent")
    public ResponseEntity<?> rent(@RequestBody Map<String, Integer> body) {
        Integer carId = body.get("carId");
        Integer days = body.get("days");
        if (carId == null || days == null) return ResponseEntity.badRequest().body("carId and days required");

        Boolean avail = repo.isAvailable(carId);
        if (avail == null) return ResponseEntity.notFound().build();
        if (!avail) return ResponseEntity.status(409).body("Car not available");

        Double pricePerDay = repo.getPricePerDay(carId);
        double discount = 0;
        if (days >= 15) discount = 0.15;
        else if (days >= 8) discount = 0.10;
        else if (days >= 5) discount = 0.05;

        double total = pricePerDay * days * (1 - discount);

        boolean success = repo.rentCar(carId);
        if (success) {
            return ResponseEntity.ok(Map.of(
                    "message", "Car rented successfully!",
                    "total", total,
                    "discountApplied", discount * 100
            ));
        } else {
            return ResponseEntity.status(500).body("Failed to rent car");
        }
    }

}
