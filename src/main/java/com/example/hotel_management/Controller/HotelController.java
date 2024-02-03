package com.example.hotel_management.Controller;

import com.example.hotel_management.DTO.ChambreDTO;
import com.example.hotel_management.DTO.HotelDTO;
import com.example.hotel_management.Entity.Chambre;
import com.example.hotel_management.Entity.Hotel;
import com.example.hotel_management.Repository.HotelRepo;
import com.example.hotel_management.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
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

        for (Chambre chambre : chambres) {
            // Set the Base64 image in each chambre
            chambre.setImageBase64(chambre.getImageBase64());
        }

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
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long id) {
        Optional<Hotel> optionalHotel = hotelService.getHotelById(id);

        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            HotelDTO hotelDTO = new HotelDTO(hotel);
            return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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

    @GetMapping("/{hotelId}/rooms/{roomId}")
    public ResponseEntity<ChambreDTO> getRoomOfHotelById(@PathVariable Long hotelId, @PathVariable Long roomId) {
        Optional<Hotel> optionalHotel = hotelService.getHotelById(hotelId);

        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            for (Chambre chambre : hotel.getChambres()) {
                if (chambre.getId().equals(roomId)) {
                    ChambreDTO chambreDTO = new ChambreDTO(chambre);
                    return new ResponseEntity<>(chambreDTO, HttpStatus.OK);
                }
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel updatedHotel) {
        try {
            Hotel updated = hotelService.updateHotel(id, updatedHotel);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/zte/{id}")
    public ResponseEntity<String> updateHotelWithImage(
            @PathVariable("id") Long id,
            @RequestParam("image") MultipartFile file,
            @RequestParam("nom") String nom,
            @RequestParam("adresse") String adresse,
            @RequestParam("ville") String ville) throws IOException, SQLException {
        byte[] img=file.getBytes();
        Blob img1=new javax.sql.rowset.serial.SerialBlob(img);
        System.out.println(img.length);
        System.out.println(nom);
        Hotel hotel =new Hotel(id,nom,adresse,ville,img1);
        hotelService.updateHotel(id,hotel);
        return ResponseEntity.ok("Hotel updated successfully");
    }


}
