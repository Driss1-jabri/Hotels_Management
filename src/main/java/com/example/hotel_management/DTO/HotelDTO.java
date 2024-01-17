package com.example.hotel_management.DTO;
import com.example.hotel_management.Entity.Chambre;
import com.example.hotel_management.Entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
    private Long id;
    private String nom;
    private String adresse;
    private List<Long> chambreIds;
    private String ville;
    private byte[] imageBytes;

    // Constructors
    public HotelDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.nom = hotel.getNom();
        this.adresse = hotel.getAdresse();
        this.chambreIds = hotel.getChambres().stream().map(Chambre::getId).collect(Collectors.toList());
        this.ville = hotel.getVille();

        try {
            // Handle the SQLException here
            this.imageBytes = hotel.getImage().getBytes(1, (int) hotel.getImage().length());
        } catch (SQLException e) {
            // You might want to log the exception or throw a runtime exception
            throw new RuntimeException("Failed to read Blob data", e);
        }
    }

    // Static method to convert a list of Hotel entities to a list of HotelDTO
    public static List<HotelDTO> convertToDTOList(List<Hotel> hotels) {
        return hotels.stream().map(HotelDTO::new).collect(Collectors.toList());
    }
}
