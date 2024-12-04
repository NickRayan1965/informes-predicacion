package com.informes_predicacion.org.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
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

//pendiente definir si poner unique constraints
@Table(
  name = "reports",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = {"date", "schedule_id"})
  }
)
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

  @Column(nullable = true, length = 250)
  private String observations;

  @OneToMany(targetEntity = ReportTerritoryItem.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "report")
  private List<ReportTerritoryItem> items = new ArrayList<>();

  public void addTerritoryItem(ReportTerritoryItem item) {
    this.items.add(item);
    item.setReport(this);
  }
  public void removeTerritoryItem(ReportTerritoryItem item) {
    this.items.remove(item);
    item.setReport(null);
  }

}
