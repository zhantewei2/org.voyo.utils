package org.voyo.utils.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.voyo.utils.jackson.YoJackson;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class YoObject {
  public static ObjectMapper objectMapper=new ObjectMapper();

  static {
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    objectMapper.registerModule(YoJackson.createModule());
  }




  public static <T> Boolean compareSameIncludeNull(T origin, T target) {
    MethodHandles.Lookup lookup= MethodHandles.lookup();
    Class<?> originClass = origin.getClass();
    Field[] fields = originClass.getDeclaredFields();

    String key;
    MethodHandle methodHandle;
    MethodType methodType;

    for(Field f:fields) {
      key = f.getName();
      key = key.substring(0, 1).toUpperCase() + key.substring(1);
      methodType= MethodType.methodType(f.getType());

      try {
        methodHandle = lookup.findVirtual(originClass,"get"+key,methodType);
      } catch (NoSuchMethodException|IllegalAccessException e) {
        continue;
      }

      if (isEqualType(methodType.returnType())) {
        try {
          Object targetVal = methodHandle.invoke(target);
          if (targetVal == null || !targetVal.equals(methodHandle.invoke(origin))) {
            return false;
          }
        } catch (Throwable e) {
        }
      }
    }

    return true;
  }

  public static <T> Boolean compareSame(T origin, T target) {
    MethodHandles.Lookup lookup=MethodHandles.lookup();

    Class<?> originClass = origin.getClass();
    Field[] fields = originClass.getDeclaredFields();

    String key;
    MethodType methodType;
    MethodHandle methodHandle;
    for(Field f:fields) {
      key = f.getName();
      key = key.substring(0, 1).toUpperCase() + key.substring(1);
      methodType = MethodType.methodType(f.getType());
      try {
        methodHandle = lookup.findVirtual(originClass,"get"+key,methodType);
      } catch (NoSuchMethodException|IllegalAccessException e) {
        continue;
      }

      if (isEqualType(methodType.returnType())) {
        try {
          Object targetVal = methodHandle.invoke(target);
          if (targetVal != null && !targetVal.equals(methodHandle.invoke(origin))) {
            return false;
          }
        } catch (Throwable e) {}
      }
    }

    return true;
  }

  public static boolean isEqualType(Class<?> classType) {
    try {
      classType.getMethod("equals", Object.class);
      return true;
    } catch (Exception var2) {
      return false;
    }
  }

  public static Map<String, Object> YoMap(String[] keys, Object[] values) {
    Map<String, Object> m = new HashMap();
    for(int i=0,len=keys.length;i<len;i++){
      m.put(keys[i],values[i]);
    }
    return m;
  }

  public static <T> String toJson(T entity) {
    if(entity==null) return null;
    try {
      return objectMapper.writer().writeValueAsString(entity);
    } catch (Exception e) {
      log.warn("Failure toJson",e);
      return  null;
    }
  }

  public static <T> T loadJson(String json, Class<T> classSource) {
    if(json==null || "".equals(json))return null;
    try {
      return objectMapper.readValue(json, classSource);
    } catch (Exception e) {
      log.warn("fail loadJson",e);
      return null;
    }
  }

  public static <T> T loadJson(String json, TypeReference<T> typeReference){
    if(json == null || "".equals(json))return null;
    try{
      return objectMapper.readValue(json,typeReference);
    }catch (Exception e){
      log.warn("fail loadJson",e);
      return null;
    }
  }

  public static String resolveHumpKey(String prefix, String key) {
    return prefix + key.substring(0, 1).toUpperCase() + key.substring(1);
  }

  public static void assign(Object b, Object a) {
    Class<?> aClass=a.getClass();
    Class<?> bClass=b.getClass();
    Field[] afs=aClass.getDeclaredFields();
    Field[] bfs=bClass.getDeclaredFields();
    String key;
    String getKey;
    String setKey;
    Method getMethod;
    Method setMethod;
    for(Field field:afs){
      key=field.getName();
      getKey=resolveHumpKey("get",key);
      setKey=resolveHumpKey("set",key);
      try {
        getMethod= aClass.getMethod(getKey);
        setMethod= bClass.getMethod(setKey,field.getType());
      }catch (Exception e){
        continue;
      }
      try {
        setMethod.invoke(b,getMethod.invoke(a));
      }catch (Exception e){}
    }

  }
}

