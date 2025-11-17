package com.dominik.barberbook.dtos.appointment;

import java.time.Instant;
import lombok.Data;

@Data
public class AppointmentUpdateRequest {
  private Instant reservationDate;
  private Integer user_id;
  private Integer service_type_id;
}
