package com.informes_predicacion.org.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informes_predicacion.org.dtos.req.CreateBlockDto;
import com.informes_predicacion.org.dtos.req.GetBlocksQueryParamsDto;
import com.informes_predicacion.org.dtos.res.BlockDto;
import com.informes_predicacion.org.dtos.res.ListResponseDto;
import com.informes_predicacion.org.services.IBlockService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/blocks")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BlockController {
  private final IBlockService blockService;

  @GetMapping
  public ListResponseDto<BlockDto> findAllByCongregation(
    @ModelAttribute GetBlocksQueryParamsDto queryParams
  ) {
    return blockService.findAllByCongregation(1L, queryParams);
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
