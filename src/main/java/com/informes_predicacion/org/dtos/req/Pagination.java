package com.informes_predicacion.org.dtos.req;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class Pagination {
  public Integer page = 1;
  public Integer pageSize = 10;

  public Pageable toPageable() {
    return PageRequest.of(page - 1, pageSize);
  }
}
