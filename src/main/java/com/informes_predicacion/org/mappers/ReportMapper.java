package com.informes_predicacion.org.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.informes_predicacion.org.dtos.req.CreateReportDto;
import com.informes_predicacion.org.dtos.res.ReportDto;
import com.informes_predicacion.org.dtos.res.ReportTerritoryItemDto;
import com.informes_predicacion.org.entities.Report;
import com.informes_predicacion.org.entities.ReportTerritoryBlockItem;
import com.informes_predicacion.org.entities.ReportTerritoryItem;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportMapper {
  private final IReportBasicMapper reportBasicMapper;
  private final IReportTerritoryItemBasicMapper reportTerritoryItemBasicMapper;
  private final IReportTerritoryBlockItemBasicMapper reportTerritoryBlockItemBasicMapper;

  public Report toEntity(CreateReportDto dto) {
    Report entity = reportBasicMapper.toEntity(dto);
    entity.setItems(dto.getItems().stream().map(item -> {
      ReportTerritoryItem entityItem = reportTerritoryItemBasicMapper.toEntity(item, entity);
      Set<ReportTerritoryBlockItem> blocks = item.getBlocks().stream().map(block -> reportTerritoryBlockItemBasicMapper.toEntity(block, entityItem)).collect(Collectors.toSet());
      entityItem.setBlocks(blocks);
      return entityItem;
    }).collect(Collectors.toSet()));
    return entity;
  }
  public ReportDto toDto(Report entity) {
    ReportDto dto = reportBasicMapper.toDto(entity);
    dto.setItems(entity.getItems().stream().map(item -> {
      ReportTerritoryItemDto itemDto = reportTerritoryItemBasicMapper.toDto(item, entity);
      
      return itemDto;
    }).collect(Collectors.toList()));
    
  }
}