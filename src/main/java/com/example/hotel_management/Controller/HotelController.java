package com.example.hotel_management.Controller;

import com.example.hotel_management.DTO.ChambreDTO;
import com.example.hotel_management.DTO.HotelDTO;
import com.example.hotel_management.Entity.Chambre;
import com.example.hotel_management.Entity.Hotel;
import com.example.hotel_management.Repository.HotelRepo;
import com.example.hotel_management.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public List<HotelDTO> getAllHotels() {
        List<Hotel> hotels= hotelService.getAllHotels();
        List<HotelDTO> hotelDTOS = new ArrayList<>() ;
        for(Hotel hotel : hotels)
        {
            HotelDTO hotelDTO = new HotelDTO(hotel);
            hotelDTOS.add(hotelDTO);
        }

        return hotelDTOS;

    }
    @GetMapping(value = "/{id}/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Chambre> getRoomsById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id).get();
        List<Chambre> chambres = hotel.getChambres();

        
        chambres.forEach(chambre -> convertImageToBase64(chambre));

        return chambres;
    }

    private void convertImageToBase64(Chambre chambre) {
        try {
            if (chambre.getImage() != null && chambre.getImage().length() > 0) {
                byte[] imageBytes = chambre.getImage().getBytes(1, (int) chambre.getImage().length());
                String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
                chambre.setImageBase64(imageBase64);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @GetMapping("/{id}")
    public HotelDTO getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id).get();
        HotelDTO hotelDTO = new HotelDTO(hotel);
        return hotelDTO;
    }

    @PostMapping
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel) {
        Hotel savedHotel = hotelService.saveHotel(hotel);
        return new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
