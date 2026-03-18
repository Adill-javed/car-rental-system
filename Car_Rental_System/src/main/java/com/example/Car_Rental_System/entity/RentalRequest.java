package com.example.Car_Rental_System.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class RentalRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long carId;
    private Long userId;
    private int days;

    private String status;

    // getters and setters
}