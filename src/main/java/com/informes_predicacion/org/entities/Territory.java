package com.informes_predicacion.org.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Table(name = "territories", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name", "congregation_id"})
})
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Territory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(nullable = true, length = 250)
  private String description;

  @ManyToOne(targetEntity = Congregation.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "congregation_id", nullable = false)
  private Congregation congregation;

  @Column(nullable = false)
  private Boolean enabled;
}
