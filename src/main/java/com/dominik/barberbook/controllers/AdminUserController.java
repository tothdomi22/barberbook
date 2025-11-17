package com.dominik.barberbook.controllers;

import com.dominik.barberbook.dtos.admin.user.UserAdminUpdateRequest;
import com.dominik.barberbook.dtos.admin.user.UserCreationRequest;
import com.dominik.barberbook.entities.Role;
import com.dominik.barberbook.mappers.UserMapper;
import com.dominik.barberbook.repositories.UserRepository;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/{id}")
  public ResponseEntity<?> getUser(@PathVariable(name = "id") Integer id) {
    var user = userRepository.findById(id).orElse(null);
    if (user == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(userMapper.toDto(user));
  }

  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody @Valid UserCreationRequest request) {
    var exists = userRepository.existsByEmail(request.getEmail());
    if (exists) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(Map.of("error", "User already exists!"));
    }
    var user = userMapper.toEntity(request);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(Role.USER);
    var savedUser = userRepository.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(savedUser));
  }

  @GetMapping("/list")
  public ResponseEntity<?> listUsers() {
    var users = userRepository.findAll();
    return ResponseEntity.status(HttpStatus.OK)
        .body(users.stream().map(userMapper::toDto).toList());
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateUser(
      @PathVariable Integer id, @RequestBody @Valid UserAdminUpdateRequest request) {
    var user = userRepository.findById(id).orElse(null);
    if (user == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found!"));
    }
    if (request.getEmail() != null) {
      if (userRepository.existsByEmail(request.getEmail())
          || request.getEmail().equals(user.getEmail())) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("error", "You cannot use that email"));
      }
    }
    userMapper.adminUpdate(request, user);
    userRepository.save(user);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
    var user = userRepository.findById(id).orElse(null);
    if (user == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error:", "User not found!"));
    }
    userRepository.delete(user);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
