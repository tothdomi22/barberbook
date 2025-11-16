package com.dominik.barberbook.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateRequest {
  @NotBlank(message = "Name is required")
  @Size(max = 255, message = "Name must be less than 255 chars")
  private String name;

  @NotBlank(message = "Email is required")
  @Email(message = "You must provide a valid email")
  private String email;

  @NotBlank(message = "Phone is required")
  @Size(max = 12, message = "Phone must be less than 12 chars")
  private String phoneNumber;
}
