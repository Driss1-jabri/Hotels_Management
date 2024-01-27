package com.example.hotel_management.DTO;

import com.example.hotel_management.Entity.Chambre;
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

    public ChambreDTO(Chambre chambre) {
        this.id = chambre.getId();
        this.nom = chambre.getNom();
        this.type = chambre.getType();
        this.prix = chambre.getPrix();
        this.disponibilite = chambre.getDisponibilite();
        this.capacite = chambre.getCapacite();
    }
}
