package com.dominik.barberbook.controllers;

import com.dominik.barberbook.dtos.appointment.AppointmentCreationRequest;
import com.dominik.barberbook.dtos.appointment.AppointmentUpdateRequest;
import com.dominik.barberbook.mappers.AppointmentMapper;
import com.dominik.barberbook.repositories.AppointmentRepository;
import com.dominik.barberbook.repositories.ServiceTypeRepository;
import com.dominik.barberbook.repositories.UserRepository;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {
  private final AppointmentRepository appointmentRepository;
  private final AppointmentMapper appointmentMapper;
  private final ServiceTypeRepository serviceTypeRepository;
  private final UserRepository userRepository;

  @GetMapping("/list")
  public ResponseEntity<?> listAppointments() {
    var appointments = appointmentRepository.findAll();
    return ResponseEntity.status(HttpStatus.OK)
        .body(appointments.stream().map(appointmentMapper::toDto).toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getAppointment(@PathVariable Integer id) {
    var appointment = appointmentRepository.findById(id).orElse(null);
    if (appointment == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("error", "Appointment with that id is not found"));
    }
    return ResponseEntity.status(HttpStatus.OK).body(appointmentMapper.toDto(appointment));
  }

  @PostMapping
  public ResponseEntity<?> createAppointment(@RequestBody AppointmentCreationRequest request) {
    var serviceType = serviceTypeRepository.findById(request.getService_type_id()).orElse(null);
    if (serviceType == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("error", "Service with that id is not found"));
    }
    if (appointmentRepository.existsOverlappingAppointment(
        request.getReservationDate(), serviceType.getLength())) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(Map.of("error", "Reservation for that already exists"));
    }
    var user = userRepository.findById(request.getUser_id()).orElse(null);
    if (user == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("error", "User with that id is not found"));
    }
    var appointment = appointmentMapper.toEntity(request);
    appointment.setUser(user);
    appointment.setServiceType(serviceType);
    var savedAppointment = appointmentRepository.save(appointment);
    return ResponseEntity.ok().body(appointmentMapper.toDto(savedAppointment));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteAppointment(@PathVariable Integer id) {
    var appointment = appointmentRepository.findById(id).orElse(null);
    if (appointment == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("error", "Appointment with that id is not found"));
    }
    appointmentRepository.delete(appointment);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateAppointment(
      @PathVariable Integer id, @RequestBody AppointmentUpdateRequest request) {
    var appointment = appointmentRepository.findById(id).orElse(null);
    if (appointment == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("error", "Appointment with that id is not found"));
    }
    if (request.getService_type_id() != null) {
      var serviceType = serviceTypeRepository.findById(request.getService_type_id()).orElse(null);
      if (serviceType == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("error", "Service with that id is not found"));
      }
      appointment.setServiceType(serviceType);
    }
    if (request.getUser_id() != null) {
      var user = userRepository.findById(request.getUser_id()).orElse(null);
      if (user == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("error", "User with that id is not found"));
      }
      appointment.setUser(user);
    }
    if (request.getReservationDate() != null) {
      if (appointmentRepository.existsOverlappingAppointment(
          request.getReservationDate(), appointment.getServiceType().getLength())) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("error", "Reservation for that already exists"));
      }
      appointment.setReservationDate(request.getReservationDate());
    }
    appointmentMapper.update(request, appointment);
    appointmentRepository.save(appointment);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
