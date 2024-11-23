package com.informes_predicacion.org.services;

import java.util.List;
import java.util.Set;

import com.informes_predicacion.org.dtos.req.CreateBlockDto;
import com.informes_predicacion.org.dtos.res.BlockDto;
import com.informes_predicacion.org.entities.Block;

public interface IBlockService {
  Set<BlockDto> findAllByCongregation(Long congregationId);
  BlockDto findById(Long id, Long congregationId);
  BlockDto createBlock(CreateBlockDto createBlockDto, Long congregationId);
  BlockDto updateBlock(Long id, CreateBlockDto createBlockDto, Long congregationId);
  void deleteBlock(Long id, Long congregationId);
  Boolean existsAllBlocksByIdAndCongregationId(Set<Long> ids, Long congregationId);
  List<Block> findManyByIdsAndTerritoryId(List<Long> ids, Long territoryId);
}
