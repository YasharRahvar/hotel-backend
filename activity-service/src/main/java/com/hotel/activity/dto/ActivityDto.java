package com.hotel.activity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActivityDto implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String userId;

}
