package com.dominik.barberbook.controllers;

import com.dominik.barberbook.mappers.UserMapper;
import com.dominik.barberbook.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
  private UserRepository userRepository;
  private UserMapper userMapper;
}
