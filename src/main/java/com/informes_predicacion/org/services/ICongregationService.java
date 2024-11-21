package com.informes_predicacion.org.services;

import java.util.List;

import com.informes_predicacion.org.dtos.CreateCongregationDto;
import com.informes_predicacion.org.entities.Congregation;

public interface ICongregationService {
  Congregation createCongregation(CreateCongregationDto dto);
  List<Congregation> getAllCongregations();
  Congregation getCongregationById(Long id);
  void deleteCongregationById(Long id);
  Congregation updateCongregation(Long id, CreateCongregationDto dto);
}
