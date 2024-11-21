package com.informes_predicacion.org.services;

import java.util.List;

import com.informes_predicacion.org.dtos.CreateUserDto;
import com.informes_predicacion.org.dtos.res.UserDto;

public interface IUserService {  
  UserDto createUser(CreateUserDto createUserDto, Long congregationId);
  List<UserDto> getAllUsers(Long congregationId);
  UserDto getUserById(Long id);
  UserDto updateUser(Long id, CreateUserDto createUserDto);
  void deleteUser(Long id);
}
