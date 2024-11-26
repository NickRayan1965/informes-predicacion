package com.informes_predicacion.org.dtos.res;

import java.util.List;

import org.springframework.data.domain.Page;

import com.informes_predicacion.org.mappers.IToDtoMapper;

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

  public static <T, E> ListResponseDto<T> from(
    Page<E> pageEntities,
    IToDtoMapper<E, T> mapper
  ) {
    return ListResponseDto.<T>builder()
      .page(pageEntities.getNumber() + 1)
      .pageSize(pageEntities.getSize())
      .totalPages(pageEntities.getTotalPages())
      .totalElements(pageEntities.getTotalElements())
      .data(pageEntities.getContent().stream().map(mapper::toDto).toList())
      .build();
  }
}
