package com.informes_predicacion.org.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserDto {
  private String names;
  private String lastNames;
  private String username;
  private String password;
}
