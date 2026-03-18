package com.example.Car_Rental_System.repository;

import com.example.Car_Rental_System.entity.RentalRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RentalRequestRepository extends JpaRepository<RentalRequest, Long> {

    List<RentalRequest> findByUserId(Long userId);

}