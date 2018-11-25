package com.hotel.simple.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping(value = "/hello")
    public String hello(){
        return "hi there";
    }
}
