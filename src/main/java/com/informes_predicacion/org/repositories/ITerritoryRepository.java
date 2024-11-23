package com.informes_predicacion.org.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.informes_predicacion.org.dtos.res.TerritoryDto;
import com.informes_predicacion.org.entities.Territory;

public interface ITerritoryRepository extends JpaRepository<Territory, Long> {

  @Query("SELECT new com.informes_predicacion.org.dtos.res.TerritoryDto(t.id, t.name, t.description, t.enabled, c.id, c.name) FROM Territory t LEFT JOIN t.congregation c WHERE c.id = ?1")
  Set<TerritoryDto> findAllWithCongregationId(Long congregationId);

  @Query("SELECT t FROM Territory t LEFT JOIN t.congregation c WHERE t.id = ?1 AND c.id = ?2")
  Optional<Territory> findByIdAndCongregationId(Long id, Long congregationId);

  @Query("SELECT COUNT(t.id) = :#{#ids.size} " +
       "FROM Territory t " +
       "WHERE t.id IN :ids AND t.congregation.id = :congregationId")
  Boolean existsAllTerritoriesByIdAndCongregationId(@Param("ids") Set<Long> ids, @Param("congregationId") Long congregationId);


  @Query("SELECT t FROM Territory t WHERE t.id IN :ids AND t.congregation.id = :congregationId")
  List<Territory> findByManyIdsAndCongregationId(Set<Long> ids, Long congregationId);

  @Query("SELECT t FROM Territory t WHERE t.congregation.id = ?1")
  Page<Territory> findAllByCongregationId(Long congregationId, Pageable pageable); 
  
}
