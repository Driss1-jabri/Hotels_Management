package com.example.hotel_management.repository;

import com.example.hotel_management.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    // You can add custom queries or methods if needed

}
