package com.example.hotel_management.Service;

import com.example.hotel_management.Entity.Hotel;
import com.example.hotel_management.Repository.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
