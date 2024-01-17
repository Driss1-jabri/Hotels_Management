package com.example.hotel_management.DTO;

import com.example.hotel_management.Entity.TypeChambre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor @Data
public class ChambreDTO {
    private Long id;
    private String nom;
    private TypeChambre type;
    private Double prix;
    private Boolean disponibilite;
    private int capacite;
    private List<ReservationDto> reservations;

    public ChambreDTO(Long id, String nom, TypeChambre type, Double prix, Boolean disponibilite, int capacite, List<ReservationDto> reservations) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.disponibilite = disponibilite;
        this.capacite = capacite;
        this.reservations = reservations;

    }

}
