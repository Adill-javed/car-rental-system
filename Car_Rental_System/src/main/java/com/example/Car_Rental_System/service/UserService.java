package com.example.Car_Rental_System.service;

import com.example.Car_Rental_System.entity.User;
import com.example.Car_Rental_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User login(String email,String password){
        return userRepository.findByEmailAndPassword(email,password);
    }
    
    public User register(User user) {
        if(userRepository.findByEmail(user.getEmail()) != null) {
            return null; // email already exists
        }
        if(user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        return userRepository.save(user);
    }
}