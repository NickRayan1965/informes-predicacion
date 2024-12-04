package com.informes_predicacion.org.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.informes_predicacion.org.entities.Schedule;

public interface ISheduleRepository extends JpaRepository<Schedule, Long> {
  
  @Query("SELECT s FROM Schedule s WHERE s.id = :id AND s.congregation.id = :congregationId")
  Optional<Schedule> findByIdAndCongregationId(Long id, Long congregationId);

  @Query("SELECT s FROM Schedule s WHERE s.congregation.id = :congregationId order by s.id desc")
  Page<Schedule> findAllByCongregationId(Long congregationId, Pageable pageable);
}
