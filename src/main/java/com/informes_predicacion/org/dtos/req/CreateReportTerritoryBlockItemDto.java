package com.informes_predicacion.org.dtos.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReportTerritoryBlockItemDto {
  private Long blockId;
  private String observations;
  private Boolean completed;
}
