package com.hotel.auth.mapper;


import com.hotel.auth.dto.CreateUserDto;
import com.hotel.auth.dto.UserDto;
import com.hotel.auth.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);

    User mapToUser(CreateUserDto createUserDto);

    CreateUserDto mapToCreateUserDto(User user);

}
