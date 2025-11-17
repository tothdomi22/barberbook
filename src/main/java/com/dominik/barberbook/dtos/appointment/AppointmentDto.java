package com.dominik.barberbook.dtos.appointment;

import com.dominik.barberbook.entities.ServiceType;
import com.dominik.barberbook.entities.User;
import java.time.Instant;
import lombok.Data;

@Data
public class AppointmentDto {
    private Integer id;
    private Instant reservationDate;
    private User user;
    private ServiceType serviceType;
}
