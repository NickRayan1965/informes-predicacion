package com.informes_predicacion.org.services;

import java.util.Set;

import com.informes_predicacion.org.dtos.req.CreateBlockDto;
import com.informes_predicacion.org.dtos.res.BlockDto;

public interface IBlockService {
  Set<BlockDto> findAllByCongregation(Long congregationId);
  BlockDto findById(Long id, Long congregationId);
  BlockDto createBlock(CreateBlockDto createBlockDto, Long congregationId);
  BlockDto updateBlock(Long id, CreateBlockDto createBlockDto, Long congregationId);
  void deleteBlock(Long id, Long congregationId);
}
