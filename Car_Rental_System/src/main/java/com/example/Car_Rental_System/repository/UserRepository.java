package com.example.Car_Rental_System.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Car_Rental_System.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

    User findByEmailAndPassword(String email,String password);

}