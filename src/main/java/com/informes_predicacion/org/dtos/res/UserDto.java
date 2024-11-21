package com.informes_predicacion.org.dtos.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
  private Long id;
  private String names;
  private String lastNames;
  private String username;
  private Long congregationId;
  private String congregationName;
  private Boolean enabled;
}
