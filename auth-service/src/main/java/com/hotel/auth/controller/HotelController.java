package com.hotel.auth.controller;

import com.hotel.auth.dto.HotelDto;
import com.hotel.auth.mapper.HotelMapper;
import com.hotel.auth.service.HotelService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController {

    private HotelService hotelService;
    private HotelMapper hotelMapper;

    public HotelController(HotelService hotelService, HotelMapper hotelMapper) {

        this.hotelService = hotelService;
        this.hotelMapper = hotelMapper;
    }

    @PostMapping(value = "/hotel")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public HotelDto createHotel(@RequestBody HotelDto hotelDto) {
        HotelDto hotelDto1 = hotelMapper.mapToHotelDto(hotelService.createHotel(hotelMapper.mapToHotel(hotelDto)));
        return hotelDto1;
    }
}
