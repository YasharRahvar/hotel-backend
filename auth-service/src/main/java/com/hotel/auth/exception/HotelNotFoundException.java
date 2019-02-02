package com.hotel.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class HotelNotFoundException extends RuntimeException{

    public HotelNotFoundException() {
        super("hotel.not.found");
    }
}
