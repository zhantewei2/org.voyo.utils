package org.voyo.utils.params;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateById<T,S> {
  @NotNull
  private T id;
  @Valid
  @NotNull
  private S body;
}
