package com.informes_predicacion.org.services;

import java.util.List;
import java.util.Set;

import com.informes_predicacion.org.dtos.req.CreateTerritoryDto;
import com.informes_predicacion.org.dtos.req.GetTerritoriesQueryParamsDto;
import com.informes_predicacion.org.dtos.res.ListResponseDto;
import com.informes_predicacion.org.dtos.res.TerritoryDto;
import com.informes_predicacion.org.entities.Territory;

public interface ITerritoryService {
  ListResponseDto<TerritoryDto> getAllTerritories(Long congregationId, GetTerritoriesQueryParamsDto queryParams);
  TerritoryDto getTerritoryById(Long id, Long congregationId);
  TerritoryDto createTerritory(CreateTerritoryDto territory, Long congregationId);
  TerritoryDto updateTerritory(Long id, Territory territory);
  void deleteTerritory(Long id, Long congregationId);
  Boolean existsAllTerritoriesByIdAndCongregationId(Set<Long> ids, Long congregationId);
  List<Territory> findByManyIdsAndCongregationId(Set<Long> ids, Long congregationId);
}
