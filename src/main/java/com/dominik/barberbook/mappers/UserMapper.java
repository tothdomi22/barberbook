package com.dominik.barberbook.mappers;

import com.dominik.barberbook.dtos.admin.user.UserAdminUpdateRequest;
import com.dominik.barberbook.dtos.admin.user.UserCreationRequest;
import com.dominik.barberbook.dtos.user.UserDto;
import com.dominik.barberbook.dtos.user.UserUpdateRequest;
import com.dominik.barberbook.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
  UserDto toDto(User user);

  User toEntity(UserCreationRequest request);

  void adminUpdate(UserAdminUpdateRequest request, @MappingTarget User user);

  void userUpdate(UserUpdateRequest request, @MappingTarget User user);
}
