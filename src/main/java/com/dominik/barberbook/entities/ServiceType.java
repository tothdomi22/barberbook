package com.dominik.barberbook.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/** Represents a service type made by the barber. */
@Getter
@Setter
@Entity
@Table(name = "service_type")
public class ServiceType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @NotNull
  @Column(name = "type")
  private String type;

  @NotNull
  @Column(name = "length")
  private LocalTime length;

  @OneToMany(mappedBy = "serviceType")
  private Set<Appointment> appointments = new LinkedHashSet<>();
}
