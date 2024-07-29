package org.voyo.utils.conf.springBeans;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@ControllerAdvice
public class AdviceYoExceptHandler {
  private static final Logger log = LoggerFactory.getLogger(AdviceYoExceptHandler.class);
  public static final String SQL_ERR = "数据执行错误";
  public static final String NORMAL_ERR = "系统服务错误";

  public AdviceYoExceptHandler() {
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<String> handleGlobalException(Exception e, HttpServletResponse res) {
    String message=e.getMessage();
    if(e instanceof IOException && message.contains("Broken pipe")){
      log.warn("IOException:{}",message);
      return null;
    }else {
      e.printStackTrace();
      log.error("Catch error: ", e);
      return this.sendBody(HttpStatus.BAD_REQUEST, new ReqBadProfile(ReqBadEnum.Normal.getCode(), "系统服务错误", e.toString()));
    }
  }

  @ExceptionHandler({MethodArgumentNotValidException.class, MissingServletRequestParameterException.class})
  public ResponseEntity<String> handleRequestParamsNotValidated(Exception e, HttpServletResponse res) throws Exception {
    String msg = "";
    if (e instanceof MissingServletRequestParameterException) {
      msg = e.getMessage();
    } else if (e instanceof MethodArgumentNotValidException) {
      MethodArgumentNotValidException e2 = (MethodArgumentNotValidException)e;
      FieldError error = (FieldError)e2.getBindingResult().getAllErrors().get(0);
      msg = String.format("%s: %s", error.getField(), error.getDefaultMessage());
    }

    ReqBadProfile<String> profile = new ReqBadProfile();
    profile.setCode(ReqBadEnum.PaymentRequired.getCode());
    profile.setMsg(msg);
    return this.sendBody(ReqBadEnum.PaymentRequired.getHttpStatus(), profile);
  }

  @ExceptionHandler({SQLException.class})
  public ResponseEntity<String> handleSQLException(Exception e, HttpServletResponse res) {
    log.error("sql error: ", e);
    return this.sendBody(HttpStatus.UNAUTHORIZED, new ReqBadProfile(ReqBadEnum.SQLError.getCode(), "数据执行错误", e.toString()));
  }

  @ExceptionHandler({ReqBad.class})
  public ResponseEntity<String> handleReqBad(ReqBad e, HttpServletResponse res) {
    return this.sendBody(e.getHttpStatus(), e.getProfile());
  }

  private ResponseEntity<String> sendBody(HttpStatus httpStatus, ReqBadProfile profile) {
    String result = null;

    try {
      ObjectMapper objectMapper = new ObjectMapper();
      result = objectMapper.writeValueAsString(profile);
    } catch (Exception var5) {
    }

    return ((ResponseEntity.BodyBuilder)ResponseEntity.status(httpStatus).header("Content-Type", new String[]{"application/json"})).body(result);
  }
}
