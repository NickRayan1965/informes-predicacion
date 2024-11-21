package com.informes_predicacion.org.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.informes_predicacion.org.entities.User;

public interface IUserRepository extends JpaRepository<User, Long> {}
