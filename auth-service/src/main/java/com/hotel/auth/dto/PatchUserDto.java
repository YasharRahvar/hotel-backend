package com.hotel.auth.dto;

import lombok.Data;

@Data
public class PatchUserDto {
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String contactEmail;
    private String dateOfBirth;
    private String telephone;
}
