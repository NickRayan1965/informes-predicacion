package com.informes_predicacion.org.dtos.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReportTerritoryBlockItemDto {
  private Long id;
  private Long blockId;
  private String blockName;
  private Long reportTerritoryItemId;
  private String observations;
  private Boolean completed;
}
