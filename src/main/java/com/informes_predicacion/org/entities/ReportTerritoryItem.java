package com.informes_predicacion.org.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "report_territory_items", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"territory_id", "report_id"})
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportTerritoryItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false, targetEntity = Territory.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "territory_id", nullable = false)
  private Territory territory;

  // @Column(nullable = false, name = "report_id")

  @ManyToOne(optional = false, targetEntity = Report.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "report_id", nullable = false)
  private Report report;

  @Column(nullable = false, length = 250)
  private String observations;

  @OneToMany(targetEntity = ReportTerritoryBlockItem.class, fetch = FetchType.EAGER)
  private Set<ReportTerritoryBlockItem> blocks;

  @Column(nullable = false)
  private Boolean completed;
}