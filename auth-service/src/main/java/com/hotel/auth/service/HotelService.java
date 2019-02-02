package com.hotel.auth.service;

import com.hotel.auth.model.Hotel;
import com.hotel.auth.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelService {

    private HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {

        this.hotelRepository = hotelRepository;
    }

    public Hotel createHotel(Hotel hotel) {
       return hotelRepository.save(hotel);
    }

    public Optional<Hotel> findHotelById(Long id) {
        return hotelRepository.findById(id);
    }
}
