package com.informes_predicacion.org.dtos.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BlockDto {
  private Long id;
  private String name;
  private String description;
  private Boolean enabled;
  private Long territoryId;
  private String territoryName;
}
