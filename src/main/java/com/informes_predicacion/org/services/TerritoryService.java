package com.informes_predicacion.org.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.informes_predicacion.org.dtos.req.CreateTerritoryDto;
import com.informes_predicacion.org.dtos.req.GetTerritoriesQueryParamsDto;
import com.informes_predicacion.org.dtos.res.ListResponseDto;
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
  public ListResponseDto<TerritoryDto> getAllTerritories(Long congregationId, GetTerritoriesQueryParamsDto queryParams) {
    Page<Territory> territoriesPage = territoryRepository.findAllByCongregationId(congregationId, PageRequest.of(queryParams.getPage() - 1, queryParams.getPageSize()));
    return ListResponseDto.<TerritoryDto>builder()
      .page(territoriesPage.getNumber() + 1)
      .pageSize(territoriesPage.getSize())
      .totalPages(territoriesPage.getTotalPages())
      .totalElements(territoriesPage.getTotalElements())
      .data(territoriesPage.getContent().stream().map(territoryMapper::entityToDto).toList())
      .build();
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

  @Override
  public Boolean existsAllTerritoriesByIdAndCongregationId(Set<Long> ids, Long congregationId) {
    return territoryRepository.existsAllTerritoriesByIdAndCongregationId(ids, congregationId);
  }

  @Override
  public List<Territory> findByManyIdsAndCongregationId(Set<Long> ids, Long congregationId) {
    List<Territory> territories = territoryRepository.findByManyIdsAndCongregationId(ids, congregationId);
    if (territories.size() != ids.size()) {
      throw new RuntimeException("Territories not found");
    }
    return territories;
  }
  
}
