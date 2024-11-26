package com.informes_predicacion.org.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.informes_predicacion.org.dtos.req.CreateScheduleDto;
import com.informes_predicacion.org.dtos.req.GetSchedulesQueryParamsDto;
import com.informes_predicacion.org.dtos.res.ListResponseDto;
import com.informes_predicacion.org.dtos.res.ScheduleDto;
import com.informes_predicacion.org.entities.Congregation;
import com.informes_predicacion.org.entities.Schedule;
import com.informes_predicacion.org.mappers.IScheduleMapper;
import com.informes_predicacion.org.repositories.ISheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService implements IScheduleService{
  private final IScheduleMapper scheduleMapper;
  private final ISheduleRepository scheduleRepository;
  private final ICongregationService congregationService;
  
  @Override
  public ListResponseDto<ScheduleDto> findAllByCongregationId(Long congregationId, GetSchedulesQueryParamsDto queryParams) {
    Page<Schedule> pageSchedules = scheduleRepository.findAllByCongregationId(congregationId, queryParams.toPageable());
    return ListResponseDto.from(pageSchedules, scheduleMapper);
  }

  @Override
  public ScheduleDto findByIdAndCongregationId(Long id, Long congregationId) {
    Optional<Schedule> schedule = scheduleRepository.findByIdAndCongregationId(id, congregationId);
    if(!schedule.isPresent()) {
      throw new RuntimeException("Schedule not found");
    }
    return scheduleMapper.toDto(schedule.get());
  }
  @Override
  public ScheduleDto create(CreateScheduleDto dto, Long congregationId) {
    Congregation congregation = congregationService.getCongregationById(congregationId);
    Schedule schedule = scheduleMapper.toEntity(dto);
    schedule.setCongregation(congregation);
    try {
      schedule = scheduleRepository.save(schedule);
      return scheduleMapper.toDto(schedule);
    } catch (Exception e) {
      return null;
    }
  }
  @Override
  public ScheduleDto update(Long id, CreateScheduleDto dto, Long congregationId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }
  @Override
  public void delete(Long id, Long congregationId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

}
