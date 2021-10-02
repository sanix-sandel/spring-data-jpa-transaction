package com.example.demo.repo;

import com.example.demo.entity.PassengerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerInfoRepo extends JpaRepository<PassengerInfo, Long> {
}
