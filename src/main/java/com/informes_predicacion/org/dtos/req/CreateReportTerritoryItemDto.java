package com.informes_predicacion.org.dtos.req;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReportTerritoryItemDto {
  private Long territoryId;
  private String observations;
  private Boolean completed;
  private List<CreateReportTerritoryBlockItemDto> blocks;
}
