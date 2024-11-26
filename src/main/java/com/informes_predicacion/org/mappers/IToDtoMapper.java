package com.informes_predicacion.org.mappers;

public interface IToDtoMapper<E, D> {
  D toDto(E entity);
}