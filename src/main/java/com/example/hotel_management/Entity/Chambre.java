package com.example.hotel_management.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.sql.SQLException;
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
    @Transient
    private String ImageBase64;

    public String getImageBase64() {
        if (image != null) {
            try {
                byte[] bytes = image.getBytes(1, (int) image.length());
                return Base64.getEncoder().encodeToString(bytes);
            } catch (SQLException e) {
                throw new RuntimeException("Error converting Blob to Base64", e);
            }
        }
        return null;
    }
}
