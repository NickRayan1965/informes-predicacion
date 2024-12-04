package com.informes_predicacion.org.classes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TerritoryDetailProgress {
  String initialDate;
  String finalDate;
  String preachingDriverOpenderCompleteName;
}
