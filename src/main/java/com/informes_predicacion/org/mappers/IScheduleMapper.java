package com.informes_predicacion.org.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.informes_predicacion.org.dtos.req.CreateScheduleDto;
import com.informes_predicacion.org.dtos.res.ScheduleDto;
import com.informes_predicacion.org.entities.Schedule;

@Mapper(componentModel = "spring")
public interface IScheduleMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "enabled", constant = "true")
  @Mapping(target = "congregation", ignore = true)
  void mergeToEntity(CreateScheduleDto dto, @MappingTarget Schedule entity);

  @Mapping(target = "congregationId", source = "congregation.id")
  @Mapping(target = "congregationName", source = "congregation.name")
  void mergeToDto(Schedule entity, @MappingTarget ScheduleDto dto);

  default ScheduleDto toDto(Schedule entity) {
    ScheduleDto dto = new ScheduleDto();
    mergeToDto(entity, dto);
    return dto;
  }
  default Schedule toEntity(CreateScheduleDto dto) {
    Schedule entity = new Schedule();
    mergeToEntity(dto, entity);
    return entity;
  }
}
