package org.voyo.utils.HttpException;

import org.springframework.http.HttpStatus;

public enum ReqBadEnum {
  Normal(1,HttpStatus.BAD_REQUEST),
  NotLogin(2,HttpStatus.UNAUTHORIZED),
  ExpiredLogin(3,HttpStatus.UNAUTHORIZED),
  NotAuth(4,HttpStatus.UNAUTHORIZED),
  PaymentRequired(5,HttpStatus.PAYMENT_REQUIRED),
  PaymentError(51,HttpStatus.PAYMENT_REQUIRED),
  NotAccept(61,HttpStatus.NOT_ACCEPTABLE),
  SQLError(110,HttpStatus.BAD_REQUEST),
  FindEmpty(101,HttpStatus.BAD_REQUEST),
  FindUnAuth(102,HttpStatus.BAD_REQUEST),
  InsertDup(201,HttpStatus.BAD_REQUEST),
  InsertFail(202,HttpStatus.EXPECTATION_FAILED),
  UpdateNotFound(301,HttpStatus.BAD_REQUEST),
  UpdateAlreadyExist(302,HttpStatus.BAD_REQUEST),
  BadFeign(407,HttpStatus.BAD_REQUEST);

  private int code;
  private HttpStatus httpStatus;
  private ReqBadEnum(int code, HttpStatus httpStatus){
    this.code=code;
    this.httpStatus=httpStatus;
  }
  public int getCode(){
    return this.code;
  }
  public HttpStatus getHttpStatus(){
    return this.httpStatus;
  }
}
