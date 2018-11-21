package com.hotel.activity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {

    @GetMapping("/hello")
    public String hello(){
        return "Hi there";
    }
}
