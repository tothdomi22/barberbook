package com.dominik.barberbook.dtos.admin.servicetype;

import com.dominik.barberbook.entities.Appointment;
import java.time.LocalTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ServiceTypeDto {
    private Integer id;
    private String type;
    private LocalTime length;
    private Set<Appointment> appointments;
}
