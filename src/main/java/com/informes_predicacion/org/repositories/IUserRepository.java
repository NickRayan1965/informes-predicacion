package com.informes_predicacion.org.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.informes_predicacion.org.entities.User;

public interface IUserRepository extends JpaRepository<User, Long> {
  @Query("SELECT u FROM User u LEFT JOIN u.congregation c WHERE u.id = ?1 AND c.id = ?2 AND u.enabled = true")
  Optional<User> findByIdAndCongregationId(Long id, Long congregationId); 
}
