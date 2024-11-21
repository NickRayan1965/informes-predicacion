package com.informes_predicacion.org.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.informes_predicacion.org.dtos.res.ScheduleDto;
import com.informes_predicacion.org.entities.Schedule;

public interface ISheduleRepository extends JpaRepository<Schedule, Long> {
  
  @Query("SELECT new com.informes_predicacion.org.dtos.res.ScheduleDto(s.id, s.name, s.time, s.enabled, s.congregation.id, s.congregation.name) FROM Schedule s LEFT JOIN s.congregation c WHERE c.id = :congregationId AND s.enabled = true")
  Set<ScheduleDto> findAllByCongregationId(Long congregationId);

  
  @Query("SELECT s FROM Schedule s WHERE s.id = :id AND s.congregation.id = :congregationId")
  Optional<Schedule> findByIdAndCongregationId(Long id, Long congregationId);
}
