package com.informes_predicacion.org.dtos.req;

import lombok.Data;

@Data
public class Pagination {
  public Integer page = 1;
  public Integer pageSize = 10;
}
