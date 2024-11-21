package com.informes_predicacion.org.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.informes_predicacion.org.dtos.req.CreateTerritoryDto;
import com.informes_predicacion.org.dtos.res.TerritoryDto;
import com.informes_predicacion.org.entities.Territory;

@Mapper(componentModel = "spring")
public interface ITerritoryMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "enabled", constant = "true")
  @Mapping(target = "congregation", ignore = true)
  void mergeToEntity(CreateTerritoryDto dto, @MappingTarget Territory entity);

  @Mapping(target = "congregationId", source = "congregation.id")
  @Mapping(target = "congregationName", source = "congregation.name")
  void mergeToDto(Territory entity, @MappingTarget TerritoryDto dto);

  default Territory dtoToEntity(CreateTerritoryDto dto) {
    Territory entity = Territory.builder().build();
    mergeToEntity(dto, entity);
    return entity;
  }

  default TerritoryDto entityToDto(Territory entity) {
    TerritoryDto dto = TerritoryDto.builder().build();
    mergeToDto(entity, dto);
    return dto;
  }

}
