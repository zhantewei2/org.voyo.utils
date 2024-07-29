package org.voyo.utils.params;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
public class UpdateByLongId<T> {
  @NotEmpty
  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  @Valid
  @NotNull
  private T body;
}
