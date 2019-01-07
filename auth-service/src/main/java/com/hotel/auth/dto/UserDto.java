package com.hotel.auth.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String title;
    private String firstName;
    private String lastName;
    private String telephone;
    private String email;
    private String contactEmail;
    private String dateOfBirth;
}
