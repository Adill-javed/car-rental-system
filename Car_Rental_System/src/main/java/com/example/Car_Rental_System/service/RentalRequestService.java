package com.example.Car_Rental_System.service;

import com.example.Car_Rental_System.entity.RentalRequest;
import com.example.Car_Rental_System.repository.RentalRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rentalRequestService")
public class RentalRequestService {

    @Autowired
    private RentalRequestRepository repository;

    public void requestCar(Long carId, Long userId, int days) {

        RentalRequest r = new RentalRequest();

        r.setCarId(carId);
        r.setUserId(userId);
        r.setDays(days);
        r.setStatus("PENDING");

        repository.save(r);
    }

    public List<RentalRequest> getAllRequests() {
        return repository.findAll();
    }

    public void approveRequest(Long id) {

        RentalRequest r = repository.findById(id).orElse(null);

        if (r != null) {
            r.setStatus("APPROVED");
            repository.save(r);
        }
    }
    public List<RentalRequest> getRequestsByUser(Long userId) {
        return repository.findByUserId(userId);
    }
}