package org.voyo.utils.params;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ByLongId {
  @NotNull
  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;
}
