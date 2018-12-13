package com.hotel.auth.controller;

import com.hotel.auth.dto.CreateUserDto;
import com.hotel.auth.mapper.UserMapper;
import com.hotel.auth.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final  UserMapper userMapper;
    private final UserService userService;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(value = "/user")
    public CreateUserDto createUser(@RequestBody CreateUserDto createUserDto) {
        return userMapper.mapToCreateUserDto(userService.createUser(userMapper.mapToUser(createUserDto)));
    }
}
