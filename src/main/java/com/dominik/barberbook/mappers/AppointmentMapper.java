package com.dominik.barberbook.mappers;

import com.dominik.barberbook.dtos.appointment.AppointmentCreationRequest;
import com.dominik.barberbook.dtos.appointment.AppointmentDto;
import com.dominik.barberbook.dtos.appointment.AppointmentUpdateRequest;
import com.dominik.barberbook.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AppointmentMapper {
  AppointmentDto toDto(Appointment appointment);

  Appointment toEntity(AppointmentCreationRequest request);

  void update(AppointmentUpdateRequest request, @MappingTarget Appointment appointment);
}
