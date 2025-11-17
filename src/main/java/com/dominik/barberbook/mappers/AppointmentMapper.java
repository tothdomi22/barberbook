package com.dominik.barberbook.mappers;

import com.dominik.barberbook.dtos.appointment.AppointmentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
  AppointmentDto toDto();
}
