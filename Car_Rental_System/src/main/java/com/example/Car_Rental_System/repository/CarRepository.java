package com.example.Car_Rental_System.repository;


import com.example.Car_Rental_System.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long>{

}