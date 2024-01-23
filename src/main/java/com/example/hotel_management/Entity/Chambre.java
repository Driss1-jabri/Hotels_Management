package com.example.hotel_management.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.util.Base64;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CHAMBRES")
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    @Enumerated(EnumType.STRING)
    private TypeChambre type;

    @ManyToOne
    @JsonBackReference
    private Hotel hotel;

    @OneToMany(mappedBy = "chambre", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Reservation> reservations;

    private Double prix;
    private Boolean disponibilite;
    private int capacite;

    @Lob
    private Blob image;
    private String imageBase64;


}
