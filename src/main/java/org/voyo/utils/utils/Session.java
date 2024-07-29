package org.voyo.utils.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public class Session {

  public static String headerKey="yo-user-info";
  public static Class<?> userEntity;
  public static ObjectMapper objectMapper=new ObjectMapper();
  public static boolean mapperInitialized=false;
  public static void setUserEntity(Class<?> userEntity1){
    userEntity=userEntity1;
  }
  public static void initMapper(){
    if(mapperInitialized)return;
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
  }
  public static HttpServletRequest getCurrentReq(){
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
  }
  //gateway assembly session.
  public static <T> ServerHttpRequest.Builder responseAssemblySession(ServerHttpRequest req, T session) throws JsonProcessingException {
    String userStr=objectMapper.writer().writeValueAsString(session);
    return req.mutate().headers(headers->{
      headers.add(headerKey,userStr);
    });

  }

  // service get session.
  public static <T> T  getSession(){
    initMapper();
    try {
      String userStr=getCurrentReq().getHeader(headerKey);
      if(userStr==null)return null;
      return (T)objectMapper.readValue(userStr,userEntity);
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }
}
