package com.informes_predicacion.org.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informes_predicacion.org.dtos.CreateUserDto;
import com.informes_predicacion.org.dtos.res.UserDto;
import com.informes_predicacion.org.services.IUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final IUserService userService;

  @GetMapping
  public List<UserDto> getAllUsers() {
    return userService.getAllUsers(null);
  }

  @PostMapping
  public UserDto create(@RequestBody CreateUserDto createUserDto) {
    return userService.createUser(createUserDto, 1L);
  }
}
