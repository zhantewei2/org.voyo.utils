package org.voyo.utils.params;

import lombok.Data;

@Data
public class Result<T> {
  private T result;
  private Integer code;
}
