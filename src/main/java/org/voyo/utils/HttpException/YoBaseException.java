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

  public YoBaseException(){

  }

  public YoBaseException(String msg){
    this.msg = msg;
    this.code= 400;
    this.statusCode = HttpStatus.BAD_REQUEST;
  }
  public YoBaseException(Integer code, String msg){
    this.code=code;
    this.statusCode= HttpStatus.BAD_REQUEST;
    this.msg=msg;
  }

  public YoBaseException(Integer code,String msg,Object data){
    this.code=code;
    this.statusCode= HttpStatus.BAD_REQUEST;
    this.msg=msg;
    this.data=data;
  }

}
