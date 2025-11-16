package com.dominik.barberbook.dtos.user;

import com.dominik.barberbook.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
  private Integer id;
  private String name;
  private String email;
  private String phoneNumber;
  private Role role;
}
