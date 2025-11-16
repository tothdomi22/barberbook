package com.dominik.barberbook.dtos.admin.servicetype;

import java.time.LocalTime;
import lombok.Data;

@Data
public class ServiceTypeUpdateRequest {
  private String type;
  private LocalTime length;
}
