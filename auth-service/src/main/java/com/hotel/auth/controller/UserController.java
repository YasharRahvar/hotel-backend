package com.hotel.auth.controller;

import com.hotel.auth.dto.CreateUserDto;
import com.hotel.auth.dto.PatchUserDto;
import com.hotel.auth.dto.UserDto;
import com.hotel.auth.dto.UserIdDto;
import com.hotel.auth.mapper.UserMapper;
import com.hotel.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import static com.hotel.auth.security.SecurityUtil.getUserId;


/**
 * User controller class.
 */
@RestController
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(value = "/user/{userId}")
    @PreAuthorize("#oauth2.hasScope('api.middleware.read')")
    public UserDto getUserForMiddleware(@PathVariable final Long userId) {
        return userMapper.mapToUserDto(userService.findUserById(userId));
    }

    @GetMapping(value = "/user/info")
    @PreAuthorize("#oauth2.hasScope('client')")
    public ResponseEntity<?> getUserInfo(final OAuth2Authentication authentication) {
        return ResponseEntity.ok(authentication);
    }

    @GetMapping(value = "/user")
    @PreAuthorize("#oauth2.hasScope('client')")
    public ResponseEntity<?> getUser(final OAuth2Authentication authentication) {
        return ResponseEntity.ok(this.userMapper.mapToUserDto(userService.findUserById(getUserId(authentication))));
    }

    @GetMapping(value = "/user/id")
    @PreAuthorize("#oauth2.hasScope('client')")
    public UserIdDto getUserAccountId(final OAuth2Authentication authentication) {
        return new UserIdDto(getUserId(authentication));
    }

    @PutMapping(value = "/user")
    @PreAuthorize("#oauth2.hasScope('client')")
    public UserDto putUser(@RequestBody UserDto userDto, final OAuth2Authentication authentication) {
        return userMapper.mapToUserDto(userService.putUser(userMapper.mapToUser(userDto), getUserId(authentication)));
    }

    @PatchMapping(value = "/user")
    @PreAuthorize("#oauth2.hasScope('client')")
    public UserDto patchUser(@RequestBody PatchUserDto patchUserDto, final OAuth2Authentication authentication) {
        return userMapper.mapToUserDto(userService.patchUser(patchUserDto, getUserId(authentication)));
    }

    @PostMapping(value = "/user")
    @PreAuthorize("#oauth2.hasScope('client')")
    public CreateUserDto createUser(@RequestBody CreateUserDto createUserDto) {
        return userMapper.mapToCreateUserDto(userService.createUser(userMapper.mapToUser(createUserDto)));
    }
}
