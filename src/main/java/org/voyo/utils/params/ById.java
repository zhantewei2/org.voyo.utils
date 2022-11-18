package org.voyo.utils.params;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class ById<T> {
  @NotNull
  private T id;
  public ById(){}
  public ById(T id){
    this.id=id;
  }
}
