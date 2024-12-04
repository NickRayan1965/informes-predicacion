package com.informes_predicacion.org.config;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.informes_predicacion.org.entities.Congregation;
import com.informes_predicacion.org.entities.Territory;
import com.informes_predicacion.org.repositories.ICongregationRepository;
import com.informes_predicacion.org.repositories.ITerritoryRepository;
import com.informes_predicacion.org.services.ITerritoryService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class StarterConfig {
  private final ObjectMapper objectMapper;
  private final ITerritoryRepository territoryRepository;
  private final ICongregationRepository congregationRepository;
  @Bean
  ApplicationRunner applicationRunner() {
    return args -> {
      Congregation congregation = createCongregation();
      createTerritories(congregation.getId());
    };
  }
  private InputStream getInputStream(String path) {
    return this.getClass().getClassLoader().getResourceAsStream(path);
  }
  private Congregation createCongregation() {
    InputStream inputStreamCongregation = null;
    Congregation congregation = new Congregation();
    try {
      inputStreamCongregation = getInputStream("congregation.json"); 
      congregation = objectMapper.readValue(inputStreamCongregation, Congregation.class);
      return congregationRepository.save(congregation);
    } catch (Exception e) {
      System.out.println("Error loading congregation " + e.getMessage());
      return congregationRepository.findOne(
          Example.of(
            Congregation.builder().name(congregation.getName()).build()
          )
        ).get();
    }
  }
  private List<Territory> createTerritories(Long congregationId) {
    try {
      InputStream inputStreamTerritories = getInputStream("territories.json");
      List<Territory> territories = objectMapper.readValue(inputStreamTerritories, new TypeReference<List<Territory>>(){});
      System.out.println("Territorio: " + territories.get(0).toString());
      territories.forEach(territory -> {
        territory.getCongregation().setId(congregationId);
      });
      return territoryRepository.saveAll(territories);
    } catch (Exception e) {
      System.out.println("Error loading territories " + e.getMessage());
      return null;
    }
  }
}
