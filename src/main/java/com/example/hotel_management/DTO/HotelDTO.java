package com.example.hotel_management.DTO;

import com.example.hotel_management.Entity.Chambre;
import com.example.hotel_management.Entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    // Constructors
    public HotelDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.nom = hotel.getNom();
        this.adresse = hotel.getAdresse();
        this.chambreIds = hotel.getChambres().stream().map(Chambre::getId).collect(Collectors.toList());
        this.ville = hotel.getVille();
    }

    // Static method to convert a list of Hotel entities to a list of HotelDTO
    public static List<HotelDTO> convertToDTOList(List<Hotel> hotels) {
        return hotels.stream().map(HotelDTO::new).collect(Collectors.toList());
    }
}
