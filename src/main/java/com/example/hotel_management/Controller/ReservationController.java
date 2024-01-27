package com.example.hotel_management.Controller;

import com.example.hotel_management.Entity.Chambre;
import com.example.hotel_management.Entity.Client;
import com.example.hotel_management.Repository.ChambreRepo;
import com.example.hotel_management.Repository.ClientRepo;
import com.example.hotel_management.Service.ClientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.hotel_management.Entity.Reservation;
import com.example.hotel_management.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
@CrossOrigin("http://localhost:3000")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private ChambreRepo chambreRepo;


    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.getReservationById(id);
        return reservation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{roomId}/{ClientId}")
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservation,@PathVariable Long roomId ,@PathVariable Long ClientId) {
        Client client = clientRepo.findById(ClientId).get();
        Chambre chambre = chambreRepo.findById(roomId).get();
        reservation.setClient(client);
        reservation.setChambre(chambre);
        Reservation savedReservation = reservationService.saveReservation(reservation);
        client.getReservations().add(savedReservation);
        chambre.getReservations().add(savedReservation);
        return  new ResponseEntity<>(savedReservation,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

