package com.informes_predicacion.org.dtos.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ScheduleDto {
  private Long id;
  private String name;
  private String time;
  private Boolean enabled;
  private Long congregationId;
  private String congregationName;
}
