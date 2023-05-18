package org.voyo.utils.params;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PgParams {
  @NotNull
  @Min(1)
  @Max(1000)
  private int pageSize;
  @NotNull
  @Min(1)
  private Integer pageNum;
}
