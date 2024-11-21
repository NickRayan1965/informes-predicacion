package com.informes_predicacion.org.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.informes_predicacion.org.dtos.req.CreateReportDto;
import com.informes_predicacion.org.dtos.res.ReportDto;
import com.informes_predicacion.org.entities.Report;

@Mapper(componentModel = "spring")
public interface IReportBasicMapper {
  
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "preachingDriver.id", source = "preachingDriverId")
  @Mapping(target = "schedule.id", source = "scheduleId")
  @Mapping(target = "items", ignore = true)
  void mergeToEntity(CreateReportDto dto, @MappingTarget Report entity);

  @Mapping(target = "preachingDriverId", source = "preachingDriver.id")
  @Mapping(target = "preachingUserNames", source = "preachingDriver.names")
  @Mapping(target = "preachingUserLastNames", source = "preachingDriver.lastNames")
  @Mapping(target = "items", ignore = true)
  @Mapping(target = "schedule.congregationId", source = "schedule.congregation.id")
  @Mapping(target = "schedule.congregationName", source = "schedule.congregation.name")
  void mergeToDto(Report entity, @MappingTarget ReportDto dto);
  
  default Report toEntity(CreateReportDto dto) {
    Report entity = new Report();
    mergeToEntity(dto, entity);
    return entity;
  }
  default ReportDto toDto(Report entity) {
    ReportDto dto = new ReportDto();
    mergeToDto(entity, dto);
    return dto;
  }
}
