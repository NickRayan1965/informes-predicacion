package com.informes_predicacion.org.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.informes_predicacion.org.dtos.CreateUserDto;
import com.informes_predicacion.org.dtos.res.UserDto;
import com.informes_predicacion.org.entities.User;

@Mapper(componentModel = "spring")
public interface IUserMapper extends IToDtoMapper<User, UserDto> {
  
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "enabled", constant = "true")
  @Mapping(target = "congregation", ignore = true)
  void mergeToEntity(CreateUserDto dto, @MappingTarget User entity);


  @Mapping(target = "congregation.id", source = "congregationId")
  @Mapping(target = "congregation.name", source = "congregationName")
  @Mapping(target = "password", ignore = true)
  void mergeToEntity(UserDto dto, @MappingTarget User entity);

  default User dtoToEntity(CreateUserDto dto) {
    User entity = User.builder().build();
    mergeToEntity(dto, entity);
    return entity; 
  }

  @Mapping(target = "congregationId", source = "entity.congregation.id")
  @Mapping(target = "congregationName", source = "entity.congregation.name")
  void mergeToDto(User entity, @MappingTarget UserDto dto);


  default UserDto toDto(User entity) {
    UserDto dto = new UserDto();
    mergeToDto(entity, dto);
    return dto;
  }
  default User toEntity(UserDto dto) {
    User entity = User.builder().build();
    mergeToEntity(dto, entity);
    return entity;
  }
}
