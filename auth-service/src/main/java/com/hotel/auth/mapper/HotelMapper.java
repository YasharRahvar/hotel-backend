package com.hotel.auth.mapper;


import com.hotel.auth.dto.HotelDto;
import com.hotel.auth.model.Hotel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    Hotel mapToHotel(HotelDto hotelDto);
    HotelDto mapToHotelDto(Hotel hotel);
}
