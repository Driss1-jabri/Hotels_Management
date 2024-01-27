package com.example.hotel_management;

import com.example.hotel_management.Entity.Chambre;
import com.example.hotel_management.Entity.Hotel;
import com.example.hotel_management.Entity.TypeChambre;
import com.example.hotel_management.Service.ChambreService;
import com.example.hotel_management.Service.HotelService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

@SpringBootApplication
public class HotelManagementApplication {


    @Autowired
    private HotelService hotelService;
    @Autowired
    private ChambreService chambreService;


    public static void main(String[] args) {
        SpringApplication.run(HotelManagementApplication.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
        initializeData();
    }

    private void initializeData() {
        Hotel Ibis = createHotel("Ibis", "Parcelle F Zone Casanearshore, Casablanca 20190", "Casablanca", "C:\\Users\\hicham\\Desktop\\react project\\Hotel_Management\\Hotels_Management_Frontend\\src\\Assets\\Images\\IbisHotel.png");
        Hotel Atlas_Sky = createHotel("Atlas Sky", "route El Jadida r.p. 8, parc de l'Office des Changes, Casablanca, Morocco", "Casablanca", "C:\\Users\\hicham\\Desktop\\react project\\Hotel_Management\\Hotels_Management_Frontend\\src\\Assets\\Images\\Atlas_SkyHotel.png");
        Hotel Hyat = createHotel("Hyatt", "Pl. des Nations Unies, Casablanca 20000", "Casablanca", "C:\\Users\\hicham\\Desktop\\react project\\Hotel_Management\\Hotels_Management_Frontend\\src\\Assets\\Images\\HyatHotel.png");
        Hotel Radisson = createHotel("Radisson", "2 Rue Mohamed Diouri, Casablanca 20300", "Casablanca", "C:\\Users\\hicham\\Desktop\\react project\\Hotel_Management\\Hotels_Management_Frontend\\src\\Assets\\Images\\RadissonHotel.png");


        hotelService.saveHotel(Ibis);
        hotelService.saveHotel(Atlas_Sky);
        hotelService.saveHotel(Hyat);
        hotelService.saveHotel(Radisson);


        initializeChambres(Ibis);
        initializeChambres(Atlas_Sky);
        initializeChambres(Hyat);
        initializeChambres(Radisson);

    }


    private void initializeChambres(Hotel hotel) {
        Chambre chambre1 = createChambre("SINGLE", TypeChambre.SINGLE, hotel, 100.0, true, 1, "C:\\Users\\hicham\\Desktop\\react project\\Hotel_Management\\Hotels_Management_Frontend\\src\\Assets\\Images\\room-8.png");
        Chambre chambre2 = createChambre("TRIPLE", TypeChambre.TRIPLE, hotel, 150.0, true, 3, "C:\\Users\\hicham\\Desktop\\react project\\Hotel_Management\\Hotels_Management_Frontend\\src\\Assets\\Images\\room-5.png");
        Chambre chambre3 = createChambre("FAMILIALE", TypeChambre.FAMILIALE, hotel, 300.0, true, 5, "C:\\Users\\hicham\\Desktop\\react project\\Hotel_Management\\Hotels_Management_Frontend\\src\\Assets\\Images\\room-7.png");
        Chambre chambre4 = createChambre("SUITE", TypeChambre.SUITE, hotel, 300.0, true, 5, "C:\\Users\\hicham\\Desktop\\react project\\Hotel_Management\\Hotels_Management_Frontend\\src\\Assets\\Images\\room-6.png");
        Chambre chambre5 = createChambre("STANDARD", TypeChambre.STANDARD, hotel, 250.0, true, 4, "C:\\Users\\hicham\\Desktop\\react project\\Hotel_Management\\Hotels_Management_Frontend\\src\\Assets\\Images\\room-2.png");

        chambreService.saveChambre(chambre1);
        chambreService.saveChambre(chambre2);
        chambreService.saveChambre(chambre3);
        chambreService.saveChambre(chambre4);
        chambreService.saveChambre(chambre5);


        // Add the created chambres to the hotel's chambre list
        hotel.getChambres().add(chambre1);
        hotel.getChambres().add(chambre2);
        hotel.getChambres().add(chambre3);
        hotel.getChambres().add(chambre4);
        hotel.getChambres().add(chambre5);

        // Update the hotel after adding chambres
        hotelService.saveHotel(hotel);
    }

    private Chambre createChambre(String nom, TypeChambre type, Hotel hotel, Double prix, Boolean disponibilite, int capacite, String imagePath) {
        Chambre chambre = new Chambre();
        chambre.setNom(nom);
        chambre.setType(type);
        chambre.setHotel(hotel);
        chambre.setPrix(prix);
        chambre.setDisponibilite(disponibilite);
        chambre.setCapacite(capacite);

        String imageBase64 = hotelService.encodeImageToBase64(imagePath);


        byte[] imageBytes = java.util.Base64.getDecoder().decode(imageBase64);
        Blob imageBlob = createBlob(imageBytes);

        chambre.setImage(imageBlob);

        return chambre;
    }

    private Hotel createHotel(String name, String address, String city, String imagePath) {
        Hotel hotel = new Hotel();
        hotel.setNom(name);
        hotel.setAdresse(address);
        hotel.setVille(city);
        hotel.setChambres(new ArrayList<>());
        String imageBase64 = hotelService.encodeImageToBase64(imagePath);

        byte[] imageBytes = java.util.Base64.getDecoder().decode(imageBase64);

        Blob imageBlob = createBlob(imageBytes);

        hotel.setImage(imageBlob);

        return hotel;
    }

    private Blob createBlob(byte[] data) {
        try {
            return new javax.sql.rowset.serial.SerialBlob(data);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Blob from byte array", e);
        }
    }


}
