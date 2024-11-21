package com.informes_predicacion.org.services;

import java.util.Set;

import com.informes_predicacion.org.dtos.req.CreateReportDto;
import com.informes_predicacion.org.dtos.res.ReportDto;
import com.informes_predicacion.org.entities.Report;

public interface IReportService {
  public Set<ReportDto> findAllByCongregationId(Long congregationId);
  public ReportDto findByIdAndCongregationId(Long id, Long congregationId);
  public Object createReport(CreateReportDto dto, Long congregationId);
  public void deleteReportByIdAndCongregationId(Long id, Long congregationId); 
  
  public Report getAndVerifyRelations(Report dto, Long congregationId);
}
