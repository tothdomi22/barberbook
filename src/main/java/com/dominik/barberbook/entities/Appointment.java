package com.dominik.barberbook.entities;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an appointment made by a user for a specific service type. Contains information about
 * the reservation date and links to the user and service.
 */
@Getter
@Setter
@Entity
@Table(name = "appointments")
public class Appointment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "reservation_date")
  private Instant reservationDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "service_type_id")
  private ServiceType serviceType;
}
