package com.informes_predicacion.org.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informes_predicacion.org.dtos.req.CreateTerritoryDto;
import com.informes_predicacion.org.dtos.req.GetTerritoriesQueryParamsDto;
import com.informes_predicacion.org.dtos.res.ListResponseDto;
import com.informes_predicacion.org.dtos.res.TerritoryDto;
import com.informes_predicacion.org.services.ITerritoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/territories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TerritoryController {
  private final ITerritoryService territoryService;
  
  @GetMapping
  public ListResponseDto<TerritoryDto> getAllTerritories(@ModelAttribute GetTerritoriesQueryParamsDto queryParams) {
    return territoryService.getAllTerritories(1L, queryParams);
  }

  @PostMapping
  public TerritoryDto create(@RequestBody CreateTerritoryDto createTerritoryDto) {
    return territoryService.createTerritory(createTerritoryDto, 1L);
  }

  //id
  @GetMapping("/{id}")
  public TerritoryDto getTerritoryById(@PathVariable Long id) {
    return territoryService.getTerritoryById(id, 1L);
  }  
}
