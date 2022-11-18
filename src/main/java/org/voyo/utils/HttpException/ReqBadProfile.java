package org.voyo.utils.HttpException;

import lombok.Data;

@Data
public class ReqBadProfile<T> {
  private int code;
  private String msg;
  private T data;

  public ReqBadProfile(){}
  public ReqBadProfile(
      int code,
      String msg,
      T data
  ){
    this.code=code;
    this.msg=msg;
    this.data=data;
  }
}
