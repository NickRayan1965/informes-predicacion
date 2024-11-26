package com.informes_predicacion.org.services;


import com.informes_predicacion.org.dtos.req.CreateScheduleDto;
import com.informes_predicacion.org.dtos.req.GetSchedulesQueryParamsDto;
import com.informes_predicacion.org.dtos.res.ListResponseDto;
import com.informes_predicacion.org.dtos.res.ScheduleDto;

public interface IScheduleService {
  public ListResponseDto<ScheduleDto> findAllByCongregationId(Long congregationId, GetSchedulesQueryParamsDto pagination);
  public ScheduleDto findByIdAndCongregationId(Long id, Long congregationId);
  public ScheduleDto create(CreateScheduleDto dto, Long congregationId);
  public ScheduleDto update(Long id, CreateScheduleDto dto, Long congregationId);
  public void delete(Long id, Long congregationId);
}
