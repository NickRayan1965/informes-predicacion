package com.informes_predicacion.org.config;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.informes_predicacion.org.entities.Block;
import com.informes_predicacion.org.entities.Congregation;
import com.informes_predicacion.org.entities.Schedule;
import com.informes_predicacion.org.entities.Territory;
import com.informes_predicacion.org.entities.User;
import com.informes_predicacion.org.repositories.IBlockRepository;
import com.informes_predicacion.org.repositories.ICongregationRepository;
import com.informes_predicacion.org.repositories.IScheduleRepository;
import com.informes_predicacion.org.repositories.ITerritoryRepository;
import com.informes_predicacion.org.repositories.IUserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class StarterConfig {
  private final ObjectMapper objectMapper;
  private final ITerritoryRepository territoryRepository;
  private final ICongregationRepository congregationRepository;
  private final IBlockRepository blockRepository;
  private final IScheduleRepository scheduleRepository;
  private final IUserRepository userRepository;
  @Bean
  ApplicationRunner applicationRunner() {
    return args -> {
      Congregation congregation = createCongregation();
      List<Territory> territories = createTerritories(congregation.getId());
      Map<String, Territory> territoriesMap = getTerritoriesMapByNames(territories);
      createBlocks(territoriesMap);
      createSchedules(congregation.getId());
      createUsers(congregation.getId());
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
  private void createSchedules(Long congregationId) {
    try {
      InputStream inputStreamSchedules = getInputStream("schedules.json");
      List<Schedule> schedules = objectMapper.readValue(inputStreamSchedules, new TypeReference<List<Schedule>>() {});
      schedules.forEach(schedule -> {
        schedule.getCongregation().setId(congregationId);
        try {
          scheduleRepository.save(schedule);
        } catch (Exception e) {
          
        }
      });
    } catch (Exception e) {
      System.out.println("Error loading schedules " + e.getMessage());
    }
  }
  private List<Territory> createTerritories(Long congregationId) {
    try {
      InputStream inputStreamTerritories = getInputStream("territories.json");
      List<Territory> territories = objectMapper.readValue(inputStreamTerritories, new TypeReference<List<Territory>>(){});
      List<Territory> territoriesRef = new ArrayList<>();
      territories.forEach(territory -> {
        try {
          territory.getCongregation().setId(congregationId);
          Territory newTerritory = territoryRepository.save(territory);
          territoriesRef.add(newTerritory);
        } catch (Exception e) {
          territoriesRef.add(territoryRepository.findOne(
            Example.of(
              Territory.builder().name(territory.getName()).build()
            )
          ).get());
        }
      });
      return territoriesRef;
    } catch (Exception e){
      System.out.println("Error loading territories " + e.getMessage());
      return null;
    }
  }
  private void createBlocks(Map<String, Territory> territoriesMap) {
    try {
      InputStream inputStreamBlocks = getInputStream("blocks.json");
      List<Block> blocks = objectMapper.readValue(inputStreamBlocks, new TypeReference<List<Block>>(){});
      blocks.forEach(block -> {
        block.setTerritory(territoriesMap.get(block.getTerritory().getName()));
        try {
          blockRepository.save(block);
        } catch (Exception e) {
          
        }
      });
    } catch (Exception e) {
      System.out.println("Error loading blocks " + e.getMessage());
    }
  }
  private void createUsers(Long congregationId) {
    try {
      InputStream inputStreamUsers = getInputStream("users.json");
      List<User> users = objectMapper.readValue(inputStreamUsers, new TypeReference<List<User>>(){});
      users.forEach(user -> {
        user.getCongregation().setId(congregationId);
        try {
          userRepository.save(user);
        } catch (Exception e) {
          
        }
      });
    } catch (Exception e) {
      System.out.println("Error loading users " + e.getMessage());
    }
  }
  private Map<String, Territory> getTerritoriesMapByNames(List<Territory> territories) {
    return territories.stream().collect(
      Collectors.toMap(Territory::getName, Function.identity())
    );
  }
}
