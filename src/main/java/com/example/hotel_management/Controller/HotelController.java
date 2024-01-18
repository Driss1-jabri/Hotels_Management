package com.example.hotel_management.Controller;

import com.example.hotel_management.DTO.HotelDTO;
import com.example.hotel_management.Entity.Hotel;
import com.example.hotel_management.Repository.HotelRepo;
import com.example.hotel_management.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @GetMapping
    public List<HotelDTO> getHotels(){
        List<Hotel> hotels = hotelService.getAllHotels();
        List<HotelDTO> hotelsDTO = new ArrayList<>();
        for(Hotel hotel : hotels){
            HotelDTO hotelDTO = new HotelDTO(hotel);
                    hotelsDTO.add(hotelDTO);
        }
        return hotelsDTO;
    }
}
