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

  private void call2(Integer code,String msg,Object data){
    this.profile=new ReqBadProfile<Object>();
    this.profile.setCode(code);
    this.profile.setData(data);
    this.profile.setMsg(msg);
    this.httpStatus=HttpStatus.BAD_REQUEST;
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

  public ReqBad(String msg){
    super(msg);
    this.call(ReqBadEnum.Normal,msg,null);
  }
  public <T> ReqBad(String msg,T data){
    super(msg);
    this.call(ReqBadEnum.Normal,msg,data);
  }
  public <T> ReqBad(ReqBadProfile<T> profile ){
    this.profile=profile;
  }

  public ReqBad(Integer code,String msg){
    super(msg);
    this.call2(code,msg,null);
  }
  public ReqBad(Integer code,String msg,Object data){
    super(msg);
    this.call2(code,msg,data);
  }

  public boolean equals(ReqBadEnum reqBadEnum){
    return this.profile.getCode() == reqBadEnum.getCode();
  }
}
