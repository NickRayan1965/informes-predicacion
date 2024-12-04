package com.informes_predicacion.org.classes;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportOfPredicationReports {
  String startDate;
  String endDate;
  Map<String, List<TerritoryDetailProgress>> details;
}
