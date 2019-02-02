package com.hotel.auth.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class HotelDto implements Serializable {

    private Long id;
    private String name;
    private String description;

}
