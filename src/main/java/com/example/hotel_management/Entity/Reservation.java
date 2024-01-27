package com.example.hotel_management.Entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RESERVATIONS")
public class Reservation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chambre_id")
    @JsonIgnore
    private Chambre chambre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_reservation")
    private Date dateReservation;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_fin_reservation")
    private Date dateFinReservation;

    @PrePersist
    public void onPersist(){
        this.dateReservation= new Date();
    }

    public Reservation(Chambre chambre, Client client, Date dateFinReservation) {
        this.chambre = chambre;
        this.client = client;
        this.dateFinReservation = dateFinReservation;
    }
}
