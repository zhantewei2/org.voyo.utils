package org.voyo.utils.params;

import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
public class UpdateById<T,S> {
  @NotNull
  private T id;
  @Valid
  @NotNull
  private S body;
}
