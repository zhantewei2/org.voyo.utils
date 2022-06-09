package org.voyo.utils.params;

import lombok.Data;

import java.util.List;

@Data
public class PgResult<T> {
  private Integer total;
  private Integer pageNum;
  private Integer pageSize;
  private List<T> list;
}
