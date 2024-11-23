package com.informes_predicacion.org.dtos.res;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListResponseDto<T> {
  private Integer page;
  private Integer pageSize;
  private Integer totalPages;
  private Long totalElements;
  private List<T> data;
}
