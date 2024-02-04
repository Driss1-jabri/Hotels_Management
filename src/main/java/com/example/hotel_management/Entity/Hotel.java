package com.example.hotel_management.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
}
