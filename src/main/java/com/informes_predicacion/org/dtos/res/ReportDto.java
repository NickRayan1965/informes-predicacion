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
public class ReportDto {
  private Long id;
  private String date;
  private ScheduleDto schedule;
  private Long preachingDriverId;
  private String preachingUserNames;
  private String preachingUserLastNames;
  private String observations;
  private List<ReportTerritoryItemDto> items;
}
