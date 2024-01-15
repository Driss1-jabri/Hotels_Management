package com.example.hotel_management.DTO;

import com.example.hotel_management.Entity.Chambre;
import com.example.hotel_management.Entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private Long id;
    private Chambre chambre;
    private Client client;
    private Date dateReservation;
    private Date dateFinReservation;
}
