package com.informes_predicacion.org.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.informes_predicacion.org.dtos.req.CreateReportTerritoryBlockItemDto;
import com.informes_predicacion.org.entities.ReportTerritoryBlockItem;
import com.informes_predicacion.org.entities.ReportTerritoryItem;

@Mapper(componentModel = "spring")
public interface IReportTerritoryBlockItemBasicMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "block.id", source = "blockId")
  @Mapping(target = "reportTerritoryItem", ignore = true)
  void mergeToEntity(CreateReportTerritoryBlockItemDto dto, @MappingTarget ReportTerritoryBlockItem entity);

  default ReportTerritoryBlockItem toEntity(CreateReportTerritoryBlockItemDto dto) {
    ReportTerritoryBlockItem entity = new ReportTerritoryBlockItem();
    mergeToEntity(dto, entity);
    return entity;
  }
  default ReportTerritoryBlockItem toEntity(CreateReportTerritoryBlockItemDto dto, ReportTerritoryItem reportTerritoryItem) {
    ReportTerritoryBlockItem entity = new ReportTerritoryBlockItem();
    mergeToEntity(dto, entity);
    entity.setReportTerritoryItem(reportTerritoryItem);
    return entity;
  }
}
