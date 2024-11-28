package com.informes_predicacion.org.dtos.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetUsersQueryParamsDto extends Pagination {
  private String completeNameFilter;

  public String getFilterCompleteNameForQuery() {
    return completeNameFilter;
  }
}
