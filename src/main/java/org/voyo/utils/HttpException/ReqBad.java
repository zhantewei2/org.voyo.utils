package org.voyo.utils.HttpException;

import org.springframework.http.HttpStatus;

public class ReqBad extends RuntimeException{
  private ReqBadProfile profile;
  private HttpStatus httpStatus;

  public ReqBadProfile getProfile(){
    return this.profile;
  }
  public HttpStatus getHttpStatus(){
    return this.httpStatus;
  }

  public <T> void call(ReqBadEnum reqBadEnum,String msg,T data){
    this.profile=new ReqBadProfile<T>();
    this.profile.setMsg(msg);
    this.profile.setData(data);
    this.httpStatus=reqBadEnum.getHttpStatus();
    this.profile.setCode(reqBadEnum.getCode());
  }
  public <T> ReqBad(ReqBadEnum reqBadEnum,String msg,T data){
    super(msg);
    this.call(reqBadEnum,msg,data);
  }
  public ReqBad(ReqBadEnum reqBadEnum){
    this.call(reqBadEnum,"",null);
  }
  public ReqBad(ReqBadEnum reqBadEnum,String msg){
    super(msg);
    this.call(reqBadEnum,msg,null);
  }
}
