package com.informes_predicacion.org.services;

import java.util.Set;

import com.informes_predicacion.org.dtos.req.CreateTerritoryDto;
import com.informes_predicacion.org.dtos.res.TerritoryDto;
import com.informes_predicacion.org.entities.Territory;

public interface ITerritoryService {
  Set<TerritoryDto> getAllTerritories(Long congregationId);
  TerritoryDto getTerritoryById(Long id, Long congregationId);
  TerritoryDto createTerritory(CreateTerritoryDto territory, Long congregationId);
  TerritoryDto updateTerritory(Long id, Territory territory);
  void deleteTerritory(Long id, Long congregationId);
  Boolean existsAllTerritoriesByIdAndCongregationId(Set<Long> ids, Long congregationId);
}
