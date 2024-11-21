package com.informes_predicacion.org.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.informes_predicacion.org.dtos.req.CreateBlockDto;
import com.informes_predicacion.org.dtos.res.BlockDto;
import com.informes_predicacion.org.entities.Block;

@Mapper(componentModel = "spring")
public interface IBlockMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "enabled", constant = "true")
  @Mapping(target = "territory.id", source = "territoryId")
  void mergeToEntity(CreateBlockDto dto, @MappingTarget Block entity);

  @Mapping(target = "territoryId", source = "territory.id")
  @Mapping(target = "territoryName", source = "territory.name")
  void mergeToDto(Block entity, @MappingTarget BlockDto dto);

  default Block dtoToEntity(CreateBlockDto dto) {
    Block entity = Block.builder().build();
    mergeToEntity(dto, entity);
    return entity;
  }

  default BlockDto entityToDto(Block entity) {
    BlockDto dto = BlockDto.builder().build();
    mergeToDto(entity, dto);
    return dto;
  }
}
