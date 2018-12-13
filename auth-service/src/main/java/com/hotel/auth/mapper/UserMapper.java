package com.hotel.auth.mapper;


import com.hotel.auth.dto.CreateUserDto;
import com.hotel.auth.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    CreateUserDto mapToCreateUserDto(User user);
    User mapToUser(CreateUserDto createUserDto);

}
