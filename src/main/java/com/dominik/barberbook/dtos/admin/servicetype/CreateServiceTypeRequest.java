package com.dominik.barberbook.dtos.admin.servicetype;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import lombok.Data;

@Data
public class CreateServiceTypeRequest {
  @NotBlank(message = "Type cannot be blank")
  private String type;

  @NotNull(message = "This field cannot be null")
  private LocalTime length;
}
