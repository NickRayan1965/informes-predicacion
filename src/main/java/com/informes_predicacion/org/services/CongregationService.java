package com.informes_predicacion.org.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.informes_predicacion.org.dtos.CreateCongregationDto;
import com.informes_predicacion.org.entities.Congregation;
import com.informes_predicacion.org.mappers.ICongregationMapper;
import com.informes_predicacion.org.repositories.ICongregationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CongregationService implements ICongregationService {

  private final ICongregationRepository congregationRepository;
  private final ICongregationMapper congregationMapper;

  @Override
  public Congregation createCongregation(CreateCongregationDto dto) {
    Congregation entity = congregationMapper.dtoToEntity(dto);
    try {
      return congregationRepository.save(entity);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public List<Congregation> getAllCongregations() {
    return congregationRepository.findAll();
  }

  @Override
  public Congregation getCongregationById(Long id) {
    Optional<Congregation> congregation = congregationRepository.findById(id);
    if (!congregation.isPresent()) {
      throw new RuntimeException("Congregation not found");
    }
    return congregation.get();
  }

  @Override
  public void deleteCongregationById(Long id) {
    Congregation congregation = getCongregationById(id);
    congregation.setEnabled(false);
    congregationRepository.save(congregation);
  }

  @Override
  public Congregation updateCongregation(Long id, CreateCongregationDto dto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateCongregation'");
  }
  
}
