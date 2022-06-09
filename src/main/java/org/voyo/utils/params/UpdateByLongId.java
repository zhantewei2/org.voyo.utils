package org.voyo.utils.params;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateByLongId<T> {
  @NotEmpty
  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  @Valid
  @NotNull
  private T body;
}
