package com.informes_predicacion.org.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "blocks", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name", "territory_id"})
})
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Block {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(nullable = true, length = 250)
  private String description;

  @ManyToOne(optional = false, targetEntity = Territory.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "territory_id", nullable = false)
  private Territory territory;

  @Column(nullable = false)
  private Boolean enabled;
}
