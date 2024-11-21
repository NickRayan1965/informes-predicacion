package com.informes_predicacion.org.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.informes_predicacion.org.entities.Congregation;

public interface ICongregationRepository extends JpaRepository<Congregation, Long> {
  
}
