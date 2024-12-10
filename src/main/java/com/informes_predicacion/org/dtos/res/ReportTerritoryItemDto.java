package com.informes_predicacion.org.dtos.res;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReportTerritoryItemDto {
  private Long id;
  private TerritoryDto territory;
  private Long reportId;
  private String observations;
  private List<ReportTerritoryBlockItemDto> blocks;
  private Boolean completed;
  private Boolean flagCompletedByDriver;
}
