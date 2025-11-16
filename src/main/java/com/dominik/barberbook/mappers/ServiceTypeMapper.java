package com.dominik.barberbook.mappers;

import com.dominik.barberbook.dtos.admin.servicetype.CreateServiceTypeRequest;
import com.dominik.barberbook.dtos.admin.servicetype.ServiceTypeDto;
import com.dominik.barberbook.dtos.admin.servicetype.ServiceTypeUpdateRequest;
import com.dominik.barberbook.entities.ServiceType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ServiceTypeMapper {
  ServiceTypeDto toDto(ServiceType serviceType);

  ServiceType toEntity(CreateServiceTypeRequest request);

  void update(ServiceTypeUpdateRequest request, @MappingTarget ServiceType serviceType);
}
