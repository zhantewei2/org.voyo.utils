package org.voyo.utils.params;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class ByList<T> {

  @Valid
  @NotNull
  private List<T> list;

  public ByList() {}
  public ByList(List<T> list){
    this.list=list;
  }


}
