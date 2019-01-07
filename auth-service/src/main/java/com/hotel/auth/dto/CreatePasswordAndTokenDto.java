package com.hotel.auth.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class CreatePasswordAndTokenDto implements Serializable {

    @NotEmpty(message = "User email null or empty")
    private String email;
    private String password;
    private String securityCode;

}