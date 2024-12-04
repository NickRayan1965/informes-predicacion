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
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "schedules",uniqueConstraints = {
   @UniqueConstraint(columnNames = {"name", "congregation_id"}),
   @UniqueConstraint(columnNames = {"start_hour", "end_hour", "congregation_id"})
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String name;

  //6pm - 8pm example 

  @Column(nullable = false, length = 5, name = "start_hour")
  private String startHour;

  @Column(nullable = false, length = 5, name = "end_hour")
  private String endHour;
  

  
  @ManyToOne(optional = false, targetEntity = Congregation.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "congregation_id", nullable = false)
  private Congregation congregation;

  @Column(nullable = false)
  private Boolean enabled;
}
