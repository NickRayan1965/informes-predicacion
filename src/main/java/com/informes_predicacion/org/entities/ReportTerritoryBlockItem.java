package com.informes_predicacion.org.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "report_territory_block_items", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"block_id", "report_territory_item_id"})
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportTerritoryBlockItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false, targetEntity = Block.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "block_id", nullable = false)
  private Block block;
  
  @JsonIgnore
  @ManyToOne(optional = false, targetEntity = ReportTerritoryItem.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "report_territory_item_id", nullable = false)
  private ReportTerritoryItem reportTerritoryItem;


  @Column(nullable = true, length = 250)
  private String observations;

  @Column(nullable = false)
  private Boolean completed;
}
