package com.informes_predicacion.org.services;


import com.informes_predicacion.org.classes.ReportOfPredicationReports;
import com.informes_predicacion.org.dtos.req.CreateReportDto;
import com.informes_predicacion.org.dtos.req.GetSchedulesQueryParamsDto;
import com.informes_predicacion.org.dtos.res.ListResponseDto;
import com.informes_predicacion.org.dtos.res.ReportDto;
import com.informes_predicacion.org.entities.Report;

public interface IReportService {
  public ListResponseDto<ReportDto> findAllByCongregationId(Long congregationId, GetSchedulesQueryParamsDto queryParams);
  public ReportDto findByIdAndCongregationId(Long id, Long congregationId);
  public Object createReport(CreateReportDto dto, Long congregationId);
  public void deleteReportByIdAndCongregationId(Long id, Long congregationId); 
  public Report getAndVerifyRelations(Report dto, Long congregationId);
  public ReportOfPredicationReports getReportOfPredicationReports(String startDate, String endDate, Long congregationId);
}
