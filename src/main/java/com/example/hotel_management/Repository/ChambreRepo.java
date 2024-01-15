package com.example.hotel_management.Repository;

import com.example.hotel_management.Entity.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChambreRepo extends JpaRepository<Chambre, Long> {
}
