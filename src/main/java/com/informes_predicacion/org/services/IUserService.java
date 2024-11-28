package com.informes_predicacion.org.services;


import com.informes_predicacion.org.dtos.CreateUserDto;
import com.informes_predicacion.org.dtos.req.GetUsersQueryParamsDto;
import com.informes_predicacion.org.dtos.res.ListResponseDto;
import com.informes_predicacion.org.dtos.res.UserDto;

public interface IUserService {  
  UserDto createUser(CreateUserDto createUserDto, Long congregationId);
  ListResponseDto<UserDto> getAllUsers(Long congregationId, GetUsersQueryParamsDto queryParams);
  UserDto getUserById(Long id);
  UserDto findByIdAndCongregationId(Long id, Long congregationId);
  UserDto updateUser(Long id, CreateUserDto createUserDto);
  void deleteUser(Long id);
}
