package com.informes_predicacion.org.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.informes_predicacion.org.dtos.req.CreateScheduleDto;
import com.informes_predicacion.org.entities.Schedule;

@Mapper(componentModel = "spring")
public interface IScheduleMapper {
  void mergeToEntity(CreateScheduleDto dto, @MappingTarget Schedule entity);
}
