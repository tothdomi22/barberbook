package com.dominik.barberbook.repositories;

import com.dominik.barberbook.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {}
