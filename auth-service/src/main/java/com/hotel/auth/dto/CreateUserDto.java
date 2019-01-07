package com.hotel.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateUserDto {

    @NotEmpty
    private String email;
}
