package com.informes_predicacion.org.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.informes_predicacion.org.dtos.req.CreateBlockDto;
import com.informes_predicacion.org.dtos.res.BlockDto;
import com.informes_predicacion.org.dtos.res.TerritoryDto;
import com.informes_predicacion.org.entities.Block;
import com.informes_predicacion.org.entities.Territory;
import com.informes_predicacion.org.mappers.IBlockMapper;
import com.informes_predicacion.org.repositories.IBlockRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlockService implements IBlockService {

  private final IBlockRepository blockRepository;
  private final IBlockMapper blockMapper;
  private final ITerritoryService territoryService;

  @Override
  public Set<BlockDto> findAllByCongregation(Long congregationId) {
    return blockRepository.findAllByCongregationId(congregationId);
  }

  @Override
  public BlockDto findById(Long id, Long congregationId) {
    Optional<Block> block = blockRepository.findById(id);
    if (!block.isPresent()) {
      throw new RuntimeException("Block not found");
    }
    return blockMapper.entityToDto(block.get());
  }

  @Override
  public BlockDto createBlock(CreateBlockDto createBlockDto, Long congregationId) {
    TerritoryDto territory = territoryService.getTerritoryById(createBlockDto.getTerritoryId(), congregationId);
    Block block = blockMapper.dtoToEntity(createBlockDto);
    block.setTerritory(Territory.builder().id(territory.getId()).name(territory.getName()).build());
    try {
      block = blockRepository.save(block);
      return blockMapper.entityToDto(block);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public BlockDto updateBlock(Long id, CreateBlockDto createBlockDto, Long congregationId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateBlock'");
  }

  @Override
  public void deleteBlock(Long id, Long congregationId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteBlock'");
  }

  @Override
  public Boolean existsAllBlocksByIdAndCongregationId(Set<Long> ids, Long congregationId) {
    return blockRepository.existsAllBlocksByIdAndCongregationId(ids, congregationId);
  }

  @Override
  public List<Block> findManyByIdsAndTerritoryId(List<Long> ids, Long territoryId) {
    List<Block> blocks = blockRepository.findManyByIdsAndTerritoryId(ids, territoryId);
    System.out.println("blocks: " + blocks);
    System.out.println("ids: " + ids);
    System.out.println("territoryId: " + territoryId);
    if (blocks.size() != ids.size()) {
      throw new RuntimeException("Some blocks not found in territory " + territoryId);
    }
    return blocks;
  }
  
}
