package com.informes_predicacion.org.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informes_predicacion.org.dtos.CreateUserDto;
import com.informes_predicacion.org.dtos.req.GetUsersQueryParamsDto;
import com.informes_predicacion.org.dtos.res.ListResponseDto;
import com.informes_predicacion.org.dtos.res.UserDto;
import com.informes_predicacion.org.services.IUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
  private final IUserService userService;

  @GetMapping
  public ListResponseDto<UserDto> getAllUsers(
    @ModelAttribute GetUsersQueryParamsDto queryParams
  ) {
    return userService.getAllUsers(1L, queryParams);
  }

  @PostMapping
  public UserDto create(@RequestBody CreateUserDto createUserDto) {
    return userService.createUser(createUserDto, 1L);
  }

}
