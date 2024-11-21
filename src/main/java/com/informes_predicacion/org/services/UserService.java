package com.informes_predicacion.org.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.informes_predicacion.org.dtos.CreateUserDto;
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
      return userMapper.entityToDto(user);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public List<UserDto> getAllUsers(Long congregationId) {
    return userRepository.findAll().stream().map(userMapper::entityToDto).toList();
  }

  @Override
  public UserDto getUserById(Long id) {
    Optional<User> user = userRepository.findById(id);
    if (!user.isPresent()) {
      throw new RuntimeException("User not found");
    }
    return userMapper.entityToDto(user.get());
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
  
}
