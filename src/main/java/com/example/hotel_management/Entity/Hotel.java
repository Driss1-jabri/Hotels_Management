package com.example.hotel_management.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HOTELS")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String adresse;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    @JsonIgnore
    private List<Chambre> chambres;

    private String ville;
    @Lob
    private Blob image;

    public Hotel(Long id, String nom, String adresse,  String ville, Blob image) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.ville = ville;
        this.image = image;
    }
}
