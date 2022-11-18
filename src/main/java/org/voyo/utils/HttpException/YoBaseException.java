package org.voyo.utils.HttpException;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class YoBaseException extends RuntimeException{
  private String msg;
  private HttpStatus statusCode;
  private Object data;
  private Integer code;

}
