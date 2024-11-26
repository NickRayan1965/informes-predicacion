package com.informes_predicacion.org.controllers;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informes_predicacion.org.dtos.req.CreateScheduleDto;
import com.informes_predicacion.org.dtos.req.GetSchedulesQueryParamsDto;
import com.informes_predicacion.org.dtos.res.ListResponseDto;
import com.informes_predicacion.org.dtos.res.ScheduleDto;
import com.informes_predicacion.org.services.IScheduleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
  private final IScheduleService scheduleService;
  @GetMapping
  public ListResponseDto<ScheduleDto> findAllByCongregationId(
  @ModelAttribute GetSchedulesQueryParamsDto queryParams) {
    return scheduleService.findAllByCongregationId(1L, queryParams);
  }

  @GetMapping("/{id}")
  public ScheduleDto findByIdAndCongregationId(@PathVariable Long id) {
    return scheduleService.findByIdAndCongregationId(id, 1L);
  }

  @PostMapping
  public ScheduleDto create(@RequestBody CreateScheduleDto dto) {
    return scheduleService.create(dto, 1L);
  }
}
