package com.dominik.barberbook.repositories;

import com.dominik.barberbook.entities.Appointment;
import java.time.Instant;
import java.time.LocalTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
  @Query(
      """
        SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
        FROM Appointment a
        WHERE a.reservationDate < FUNCTION('ADDTIME', :newStart, :duration)
          AND FUNCTION('ADDTIME', a.reservationDate, a.serviceType.length) > :newStart
    """)
  boolean existsOverlappingAppointment(
          @Param("newStart") Instant newStart, @Param("duration") LocalTime duration);
}
