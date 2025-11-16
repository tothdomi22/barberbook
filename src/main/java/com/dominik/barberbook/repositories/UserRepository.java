package com.dominik.barberbook.repositories;

import com.dominik.barberbook.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for performing CRUD operations on {@link User} entities. Extends Spring Data
 * JPA's {@link JpaRepository} to provide standard database operations.
 */
public interface UserRepository extends JpaRepository<User, Integer> {}
