package com.dominik.barberbook.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.*;

/** Represents the user. Contains information about their name, phone number and credentials. */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @NotNull
  @Column(name = "email")
  private String email;

  @NotNull
  @Column(name = "name")
  private String name;

  @NotNull
  @Column(name = "phone_number")
  private String phoneNumber;

  @NotNull
  @Column(name = "password")
  private String password;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "user")
  private Set<Appointment> appointments = new LinkedHashSet<>();
}
