package com.dominik.barberbook.dtos;

import com.dominik.barberbook.entities.Role;
import lombok.Data;

@Data
public class UserAdminUpdateRequest {
  private String name;
  private String email;
  private String phoneNumber;
  private String password;
  private Role role;
}
