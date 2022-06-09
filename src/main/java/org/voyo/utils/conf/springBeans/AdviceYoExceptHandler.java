package org.voyo.utils.conf.springBeans;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.voyo.utils.HttpException.ReqBad;
import org.voyo.utils.HttpException.ReqBadEnum;
import org.voyo.utils.HttpException.ReqBadProfile;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Slf4j
public class AdviceYoExceptHandler{
  //parameters
  @ExceptionHandler({MethodArgumentNotValidException.class, MissingServletRequestParameterException.class})
  public ResponseEntity<String> handleRequestParamsNotValidated(
    Exception e,
    HttpServletResponse res
  ) throws Exception{
    String msg="";
    if(e instanceof MissingServletRequestParameterException){
      msg=e.getMessage();
    }else if(e instanceof MethodArgumentNotValidException){
      MethodArgumentNotValidException e2=(MethodArgumentNotValidException)e;
      FieldError error= (FieldError)e2.getBindingResult().getAllErrors().get(0);
      msg= String.format("%s: %s", error.getField(),error.getDefaultMessage());

    }
    ReqBadProfile<String> profile=new ReqBadProfile<String>();
    profile.setCode( ReqBadEnum.PaymentRequired.getCode());
    profile.setMsg(msg);
    return sendBody(ReqBadEnum.PaymentRequired.getHttpStatus(), profile);
  }

  //request
  @ExceptionHandler(ReqBad.class)
  public ResponseEntity<String> handleReqBad(
    ReqBad e,
    HttpServletResponse res
  ){
    return sendBody(e.getHttpStatus(),e.getProfile());
  }
  private ResponseEntity<String> sendBody(HttpStatus httpStatus,ReqBadProfile profile){
    String result= null;
    try{
      ObjectMapper objectMapper=new ObjectMapper();
      result= objectMapper.writeValueAsString(profile);
    }catch (Exception e){ }
    return ResponseEntity
      .status(httpStatus)
      .header("Content-Type","application/json")
      .body(result);
  }
}
