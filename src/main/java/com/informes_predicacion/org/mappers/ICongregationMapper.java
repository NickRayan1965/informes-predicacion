package com.informes_predicacion.org.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.informes_predicacion.org.dtos.CreateCongregationDto;
import com.informes_predicacion.org.entities.Congregation;

@Mapper(componentModel = "spring")
public interface ICongregationMapper {

  default Congregation dtoToEntity(CreateCongregationDto dto) {
    Congregation entity = Congregation.builder().build();
    mergeToEntity(dto, entity);
    return entity; 
  }
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "enabled", constant = "true")
  void mergeToEntity(CreateCongregationDto dto, @MappingTarget Congregation entity);
}
