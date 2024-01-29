package com.example.hotel_management.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private List<ReservationDto> reservations;

    public ClientDTO(Long id, String nom, String prenom, String email, String telephone, List<ReservationDto> reservations) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.reservations = reservations;

    }
}

