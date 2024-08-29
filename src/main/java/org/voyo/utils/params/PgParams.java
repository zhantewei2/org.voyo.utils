package org.voyo.utils.params;

import lombok.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
