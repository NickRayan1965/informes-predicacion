package com.informes_predicacion.org.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Table(name = "users")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 200)
  private String names;

  @Column(nullable = false, length = 200, name = "last_names")
  private String lastNames;

  @Column(nullable = false, length = 50, unique = true)
  private String username;

  @Column(nullable = false, length = 200)
  private String password;

  @ManyToOne(optional = false, targetEntity = Congregation.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "congregation_id", nullable = false)
  private Congregation congregation;

  @Column(nullable = false)
  private Boolean enabled;
}
