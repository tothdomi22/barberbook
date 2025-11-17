package com.dominik.barberbook.dtos.appointment;

import com.dominik.barberbook.dtos.admin.servicetype.ServiceTypeDto;
import com.dominik.barberbook.dtos.user.UserDto;
import java.time.Instant;
import lombok.Data;

@Data
public class AppointmentDto {
  private Integer id;
  private Instant reservationDate;
  private UserDto user;
  private ServiceTypeDto serviceType;
}
