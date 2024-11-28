package com.informes_predicacion.org.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.informes_predicacion.org.dtos.CreateUserDto;
import com.informes_predicacion.org.dtos.req.GetUsersQueryParamsDto;
import com.informes_predicacion.org.dtos.res.ListResponseDto;
import com.informes_predicacion.org.dtos.res.UserDto;
import com.informes_predicacion.org.entities.Congregation;
import com.informes_predicacion.org.entities.User;
import com.informes_predicacion.org.mappers.IUserMapper;
import com.informes_predicacion.org.repositories.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

  private final IUserRepository userRepository;
  private final IUserMapper userMapper;
  private final ICongregationService congregationService;
  @Override
  public UserDto createUser(CreateUserDto createUserDto, Long congregationId) {
    User user = userMapper.dtoToEntity(createUserDto);
    Congregation congregation = congregationService.getCongregationById(congregationId);
    user.setCongregation(congregation);
    try {
      user = userRepository.save(user);
      return userMapper.toDto(user);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public ListResponseDto<UserDto> getAllUsers(Long congregationId, GetUsersQueryParamsDto queryParams) {
    Page<User> pageUsers = userRepository.findAllByCongregationId(congregationId,queryParams);
    return ListResponseDto.from(pageUsers, userMapper); 
  }

  @Override
  public UserDto getUserById(Long id) {
    Optional<User> user = userRepository.findById(id);
    if (!user.isPresent()) {
      throw new RuntimeException("User not found");
    }
    return userMapper.toDto(user.get());
  }

  @Override
  public UserDto updateUser(Long id, CreateUserDto createUserDto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
  }

  @Override
  public void deleteUser(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    user.setEnabled(false);
    userRepository.save(user);
  }

  @Override
  public UserDto findByIdAndCongregationId(Long id, Long congregationId) {
    Optional<User> user = userRepository.findByIdAndCongregationId(id, congregationId);
    if (!user.isPresent()) {
      throw new RuntimeException("User with id " + id + " not found");
    }
    return userMapper.toDto(user.get());
  }
  
}
