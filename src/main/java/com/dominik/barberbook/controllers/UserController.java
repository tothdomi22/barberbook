package com.dominik.barberbook.controllers;

import com.dominik.barberbook.mappers.UserMapper;
import com.dominik.barberbook.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/users")
public class UserController {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @PostMapping("/{id}")
  public ResponseEntity<?> getUser(@PathVariable Integer id) {
    var user = userRepository.findById(id).orElse(null);
    if (user == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(userMapper.toDto(user));
  }
}
