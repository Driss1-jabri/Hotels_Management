package com.example.hotel_management.Repository;

import com.example.hotel_management.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
}
