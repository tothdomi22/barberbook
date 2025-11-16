package com.dominik.barberbook.mappers;

import com.dominik.barberbook.dtos.UserDto;
import com.dominik.barberbook.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDto toDto(User user);
}
