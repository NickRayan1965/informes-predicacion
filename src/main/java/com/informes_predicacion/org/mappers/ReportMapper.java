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
    // entity.setItems(dto.getItems().stream().map(item -> {
    //   ReportTerritoryItem entityItem = reportTerritoryItemBasicMapper.toEntity(item);
    //   Set<ReportTerritoryBlockItem> blocks = item.getBlocks().stream().map(block -> reportTerritoryBlockItemBasicMapper.toEntity(block)).collect(Collectors.toSet());
    //   entityItem.setBlocks(blocks);
    //   return entityItem;
    // }).collect(Collectors.toSet()));
    if (dto.getItems() != null) {
      dto.getItems().forEach(item -> {
        ReportTerritoryItem entityItem = reportTerritoryItemBasicMapper.toEntity(item);
        entity.addTerritoryItem(entityItem);
      });
    }
    return entity;
  }
  public ReportDto toDto(Report entity) {
    ReportDto dto = reportBasicMapper.toDto(entity);
    dto.setItems(entity.getItems().stream().map(item -> {
      ReportTerritoryItemDto itemDto = reportTerritoryItemBasicMapper.toDto(item, entity);
      itemDto.setBlocks(
        item.getBlocks().stream().map(block -> reportTerritoryBlockItemBasicMapper.toDto(block)).collect(Collectors.toList())
      );
      return itemDto;
    }).collect(Collectors.toList()));
    return dto;
  }
}
