package com.example.hotel_management.Service;

import com.example.hotel_management.Entity.Hotel;
import com.example.hotel_management.Repository.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepo hotelRepo;
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepo.findById(id);
    }

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepo.save(hotel);
    }

    public void deleteHotel(Long id) {
        hotelRepo.deleteById(id);
    }
    public String encodeImageToBase64(String imagePath) {
        try {
            Path path = Paths.get(imagePath);
            byte[] imageData = Files.readAllBytes(path);
            return Base64.getEncoder().encodeToString(imageData);
        } catch (Exception e) {
            // Handle the exception or throw it as a runtime exception
            throw new RuntimeException("Error encoding image to base64", e);
        }
    }
    public Hotel updateHotel(Long id, Hotel updatedHotel) {
        Optional<Hotel> optionalHotel = hotelRepo.findById(id);

        if (optionalHotel.isPresent()) {
            Hotel existingHotel = optionalHotel.get();

            existingHotel.setNom(updatedHotel.getNom());
            existingHotel.setAdresse(updatedHotel.getAdresse());
            existingHotel.setVille(updatedHotel.getVille());
            existingHotel.setImage(updatedHotel.getImage());

            return hotelRepo.save(existingHotel);
        } else {

            throw new RuntimeException("Hotel not found with id: " + id);
        }
    }

}
