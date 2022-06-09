package org.voyo.utils.HttpException;

import lombok.Data;

@Data
public class ReqBadProfile<T> {
  private int code;
  private String msg;
  private T data;
}
