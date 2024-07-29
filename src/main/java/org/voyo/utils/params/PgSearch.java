package org.voyo.utils.params;

import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class PgSearch<T> {
  @NotNull
  @Min(1)
  @Max(1000)
  private Integer pageSize;
  @NotNull
  @Min(1)
  private Integer pageNum;
  @NotNull
  @Valid
  private T search;
}
