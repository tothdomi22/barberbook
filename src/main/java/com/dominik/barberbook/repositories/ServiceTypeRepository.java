package com.dominik.barberbook.repositories;

import com.dominik.barberbook.entities.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer> {
  Object findByType(String type);

  Boolean existsByType(String type);
}
