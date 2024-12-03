package com.informes_predicacion.org.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.informes_predicacion.org.entities.Report;

public interface IReportRepository extends JpaRepository<Report, Long> {

  @Query("SELECT r FROM Report r LEFT JOIN r.schedule s LEFT JOIN r.preachingDriver pd  WHERE pd.congregation.id = :congregationId")
  Set<Report> findAllByCongregationId(Long congregationId);

  @Query("SELECT r FROM Report r LEFT JOIN r.schedule s LEFT JOIN r.preachingDriver pd LEFT JOIN r.items i WHERE r.id = :id AND pd.congregation.id = :congregationId")
  Optional<Report> findByIdAndCongregationId(Long id, Long congregationId);

  @Query("""
    SELECT r FROM Report r LEFT JOIN r.schedule s 
    LEFT JOIN r.preachingDriver pd 
    WHERE pd.congregation.id = :congregationId
  """)
  Page<Report> findAllByCongregationId(Long congregationId, Pageable pageable); 
}
