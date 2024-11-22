package com.example.demo.repository;

import com.example.demo.model.Service_salon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceSalonRepository extends JpaRepository<Service_salon, Long> {
    // Поиск услуг по названию
    List<Service_salon> findByNameContainingIgnoreCase(String name);
}
