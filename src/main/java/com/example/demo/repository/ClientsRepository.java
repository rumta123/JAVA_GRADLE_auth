package com.example.demo.repository;

import com.example.demo.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Clients, Long> {
    Clients findByEmail(String email);
}