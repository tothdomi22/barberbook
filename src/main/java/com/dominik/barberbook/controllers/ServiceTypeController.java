package com.dominik.barberbook.controllers;

import com.dominik.barberbook.dtos.admin.servicetype.CreateServiceTypeRequest;
import com.dominik.barberbook.dtos.admin.servicetype.ServiceTypeUpdateRequest;
import com.dominik.barberbook.mappers.ServiceTypeMapper;
import com.dominik.barberbook.repositories.ServiceTypeRepository;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service-types")
@AllArgsConstructor
public class ServiceTypeController {
  private final ServiceTypeRepository serviceTypeRepository;
  private final ServiceTypeMapper serviceTypeMapper;

  @PostMapping
  public ResponseEntity<?> createServiceType(@RequestBody @Valid CreateServiceTypeRequest request) {
    var typeExists = serviceTypeRepository.existsByType(request.getType().toLowerCase());
    if (typeExists) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(Map.of("Error:", "Type already exists"));
    }
    var serviceType = serviceTypeMapper.toEntity(request);
    serviceType.normalizeType();
    var savedServiceType = serviceTypeRepository.save(serviceType);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedServiceType);
  }

  @GetMapping("/list")
  public ResponseEntity<?> listServiceTypes() {
    var serviceTypes = serviceTypeRepository.findAll();
    return ResponseEntity.status(HttpStatus.OK)
        .body(serviceTypes.stream().map(serviceTypeMapper::toDto).toList());
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateServiceType(
      @PathVariable int id, @RequestBody ServiceTypeUpdateRequest request) {
    var serviceType = serviceTypeRepository.findById(id).orElse(null);
    if (serviceType == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("error", "ServiceType not found!"));
    }
    if (request.getType() != null) {
      var typeExists = serviceTypeRepository.existsByType(request.getType().toLowerCase());
      if (typeExists) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("Error:", "Type already exists"));
      }
    }

    serviceTypeMapper.update(request, serviceType);
    var savedServiceType = serviceTypeMapper.toDto(serviceTypeRepository.save(serviceType));
    return ResponseEntity.status(HttpStatus.OK).body(savedServiceType);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteServiceType(@PathVariable int id) {
    var serviceType = serviceTypeRepository.findById(id).orElse(null);
    if (serviceType == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("Error:", "Service type with that ID is not found"));
    }
    serviceTypeRepository.delete(serviceType);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
