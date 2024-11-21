package com.informes_predicacion.org.dtos.req;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReportDto {
  private String date;
  private Long scheduleId;
  private Long preachingDriverId;
  private String observations;
  private List<CreateReportTerritoryItemDto> items;
}
