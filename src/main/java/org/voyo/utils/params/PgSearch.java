package org.voyo.utils.params;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PgSearch<T> {
  @NotNull
  @Min(1)
  @Max(100)
  private Integer pageSize;
  @NotNull
  @Min(1)
  private Integer pageNum;
  @NotNull
  @Valid
  private T search;
}
