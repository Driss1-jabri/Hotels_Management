package com.example.hotel_management.Service;

import com.example.hotel_management.Entity.Booking;
import com.example.hotel_management.Repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepository;

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void updateBooking(Long id, Booking updatedBooking) {
        Optional<Booking> existingBookingOptional = bookingRepository.findById(id);
        if (existingBookingOptional.isPresent()) {
            Booking existingBooking = existingBookingOptional.get();
            existingBooking.setCheckInDate(updatedBooking.getCheckInDate());
            existingBooking.setCheckOutDate(updatedBooking.getCheckOutDate());
            bookingRepository.save(existingBooking);
        }
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
    public void deleteAllBookings() {
        bookingRepository.deleteAll();
    }
}