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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//pendiente definir si poner unique constraints
@Table(name = "reports")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 10)
  private String date;

  @ManyToOne(optional = false, targetEntity = Schedule.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "schedule_id", nullable = false)
  private Schedule schedule;

  @ManyToOne(optional = false, targetEntity = User.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "preaching_driver_id", nullable = false)
  private User preachingDriver;

  @Column(nullable = false, length = 250)
  private String observations;

  @OneToMany(targetEntity = ReportTerritoryItem.class, fetch = FetchType.EAGER)
  private Set<ReportTerritoryItem> items;
}