package com.informes_predicacion.org.services;

import java.util.Set;

import com.informes_predicacion.org.dtos.req.CreateScheduleDto;
import com.informes_predicacion.org.dtos.res.ScheduleDto;

public interface IScheduleService {
  public Set<ScheduleDto> findAllByCongregationId(Long congregationId);
  public ScheduleDto findByIdAndCongregationId(Long id, Long congregationId);
  public ScheduleDto create(CreateScheduleDto dto, Long congregationId);
  public ScheduleDto update(Long id, CreateScheduleDto dto, Long congregationId);
  public void delete(Long id, Long congregationId);
}
