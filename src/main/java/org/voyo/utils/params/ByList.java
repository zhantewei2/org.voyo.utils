package org.voyo.utils.params;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ByList<T> {

  @Valid
  @NotNull
  private List<T> list;
}
