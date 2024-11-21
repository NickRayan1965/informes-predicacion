package com.informes_predicacion.org.controllers;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informes_predicacion.org.dtos.req.CreateReportDto;
import com.informes_predicacion.org.dtos.res.ReportDto;
import com.informes_predicacion.org.services.IReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
  private final IReportService reportService;
  
  @GetMapping
  public Set<ReportDto> findAllByCongregationId() {
    return reportService.findAllByCongregationId(1L);
  }
  @GetMapping("/{id}")
  public ReportDto findByIdAndCongregationId(@PathVariable Long id) {
    return reportService.findByIdAndCongregationId(id, 1L);
  }
  @PostMapping
  public Object createReport(@RequestBody CreateReportDto dto) {
    return reportService.createReport(dto, 1L);
  }

}
