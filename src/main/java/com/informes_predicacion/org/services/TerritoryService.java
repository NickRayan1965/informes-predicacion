package com.informes_predicacion.org.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.informes_predicacion.org.dtos.req.CreateTerritoryDto;
import com.informes_predicacion.org.dtos.res.TerritoryDto;
import com.informes_predicacion.org.entities.Congregation;
import com.informes_predicacion.org.entities.Territory;
import com.informes_predicacion.org.mappers.ITerritoryMapper;
import com.informes_predicacion.org.repositories.ITerritoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TerritoryService implements ITerritoryService {
  private final ITerritoryRepository territoryRepository;
  private final ICongregationService congregationService;
  private final ITerritoryMapper territoryMapper;
  @Override
  public Set<TerritoryDto> getAllTerritories(Long congregationId) {
    return territoryRepository.findAllWithCongregationId(congregationId);
  }

  @Override
  public TerritoryDto getTerritoryById(Long id, Long congregationId) {
    Optional<Territory> territory = territoryRepository.findByIdAndCongregationId(id, congregationId);
    if (!territory.isPresent()) {
      throw new RuntimeException("Territory not found");
    }
    return territoryMapper.entityToDto(territory.get());
  }

  @Override
  public TerritoryDto createTerritory(CreateTerritoryDto territory, Long congregationId) {
    Congregation congregation = congregationService.getCongregationById(congregationId);
    Territory newTerritory = territoryMapper.dtoToEntity(territory);
    newTerritory.setCongregation(congregation);
    try {
      newTerritory = territoryRepository.save(newTerritory);
      return territoryMapper.entityToDto(newTerritory);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public TerritoryDto updateTerritory(Long id, Territory territory) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateTerritory'");
  }

  @Override
  public void deleteTerritory(Long id, Long congregationId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteTerritory'");
  }
  
}
