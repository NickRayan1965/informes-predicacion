package com.informes_predicacion.org.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informes_predicacion.org.dtos.CreateCongregationDto;
import com.informes_predicacion.org.entities.Congregation;
import com.informes_predicacion.org.services.ICongregationService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/congregations")
@RequiredArgsConstructor
public class CongregationController {
  private final ICongregationService congregationService;
  
  @GetMapping
  public List<Congregation> getAllCongregations() {
    return congregationService.getAllCongregations();
  }

  @PostMapping
  public Congregation create(@RequestBody CreateCongregationDto createCongregationDto) {
    return congregationService.createCongregation(createCongregationDto);
  }

  //id
  @GetMapping("/{id}")
  public Congregation getCongregationById(@PathVariable Long id) {
    return congregationService.getCongregationById(id);
  }  
}
