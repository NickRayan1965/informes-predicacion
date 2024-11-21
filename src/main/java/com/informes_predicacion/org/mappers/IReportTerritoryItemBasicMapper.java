package com.informes_predicacion.org.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.informes_predicacion.org.dtos.req.CreateReportTerritoryItemDto;
import com.informes_predicacion.org.dtos.res.ReportTerritoryItemDto;
import com.informes_predicacion.org.entities.Report;
import com.informes_predicacion.org.entities.ReportTerritoryItem;

@Mapper(componentModel = "spring")
public interface IReportTerritoryItemBasicMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "territory.id", source = "territoryId")
  @Mapping(target = "blocks", ignore = true)
  @Mapping(target = "report", ignore = true)
  void mergeToEntity(CreateReportTerritoryItemDto dto, @MappingTarget ReportTerritoryItem entity);

  @Mapping(target = "reportId", source = "report.id")
  @Mapping(target = "territory.congregationId", source = "territory.congregation.id")
  @Mapping(target = "territory.congregationName", source = "territory.congregation.name")
  @Mapping(target = "blocks", ignore = true)
  void mergeToDto(ReportTerritoryItem entity, @MappingTarget ReportTerritoryItemDto dto);

  default ReportTerritoryItem toEntity(CreateReportTerritoryItemDto dto, Report report) {
    ReportTerritoryItem entity = new ReportTerritoryItem();
    mergeToEntity(dto, entity);
      entity.setReport(report);
    return entity;
  }
  default ReportTerritoryItem toEntity(CreateReportTerritoryItemDto dto) {
    ReportTerritoryItem entity = new ReportTerritoryItem();
    mergeToEntity(dto, entity);
    return entity;
  }
  default ReportTerritoryItemDto toDto(ReportTerritoryItem entity) {
    ReportTerritoryItemDto dto = new ReportTerritoryItemDto();
    mergeToDto(entity, dto);
    return dto;
  }
  default ReportTerritoryItemDto toDto(ReportTerritoryItem entity, Report report) {
    ReportTerritoryItemDto dto = new ReportTerritoryItemDto();
    mergeToDto(entity, dto);
    dto.setReportId(report.getId());
    return dto;
  }
}
