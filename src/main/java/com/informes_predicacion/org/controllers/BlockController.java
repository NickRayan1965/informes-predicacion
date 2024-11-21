package com.informes_predicacion.org.controllers;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informes_predicacion.org.dtos.req.CreateBlockDto;
import com.informes_predicacion.org.dtos.res.BlockDto;
import com.informes_predicacion.org.services.IBlockService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/blocks")
@RequiredArgsConstructor
public class BlockController {
  private final IBlockService blockService;

  @GetMapping
  public Set<BlockDto> findAllByCongregation() {
    return blockService.findAllByCongregation(1L);
  }

  @GetMapping("/{id}")
  public BlockDto findById(@PathVariable Long id) {
    return blockService.findById(id, 1L);
  }
  
  @PostMapping
  public BlockDto createBlock(@RequestBody CreateBlockDto createBlockDto) {
    return blockService.createBlock(createBlockDto, 1L);
  } 
}
