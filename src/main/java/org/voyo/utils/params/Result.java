package org.voyo.utils.params;

import lombok.Data;

@Data
public class Result<T> {
  private T result;
  private Integer code;

  public Result(){}
  public Result(Integer code,T result){
    this.code=code;
    this.result=result;
  }
}
