package org.voyo.utils.params;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class ByLongId {
  @NotNull
  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;

  public ByLongId(Long id){
    this.id=id;
  }
  public ByLongId(){

  }
}
