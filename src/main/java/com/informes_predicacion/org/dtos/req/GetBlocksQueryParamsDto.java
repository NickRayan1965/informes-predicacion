package com.informes_predicacion.org.dtos.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetBlocksQueryParamsDto extends Pagination {
  Long territoryId;
}
