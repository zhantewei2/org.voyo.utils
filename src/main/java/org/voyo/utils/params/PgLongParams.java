package org.voyo.utils.params;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PgLongParams {
  @NotNull
  @Min(1)
  @Max(3000)
  private Long pageSize;
  @NotNull
  @Min(1)
  private Long pageNum;
}
