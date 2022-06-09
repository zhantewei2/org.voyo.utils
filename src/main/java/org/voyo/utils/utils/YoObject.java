package org.voyo.utils.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class YoObject {
  /**
   * 比较包括null
   * @param origin
   * @param target
   * @param <T>
   * @return
   */
  public static <T> Boolean compareSameIncludeNull(T origin,T target){
    Class<?> sameClass= origin.getClass();
    Field[] fields= sameClass.getDeclaredFields();
    Field f;
    String key;
    java.lang.reflect.Method getMethod;
    Class<?> keyType;
    for(int i=0,len=fields.length;i<len;i++){
      f=fields[i];
      key=f.getName();
      key=key.substring(0,1).toUpperCase()+ key.substring(1);
      try {
        getMethod = sameClass.getMethod("get" + key);
      }catch (NoSuchMethodException e){
        continue;
      }
      keyType=f.getType();
      if(isEqualType(keyType)){
        try {
          Object targetVal = getMethod.invoke(target);
          if (targetVal == null || !targetVal.equals(getMethod.invoke(origin))) return false;
        }catch (Exception e){
          //continue;
        }
      }
    }
    return true;
  }

  /**
   * 排除null比较
   * @param origin
   * @param target
   * @param <T>
   * @return
   */
  public static <T> Boolean compareSame(T origin,T target){
    Class<?> sameClass= origin.getClass();
    Field[] fields= sameClass.getDeclaredFields();
    Field f;
    String key;
    java.lang.reflect.Method getMethod;
    Class<?> keyType;
    for(int i=0,len=fields.length;i<len;i++){
      f=fields[i];
      key=f.getName();
      key=key.substring(0,1).toUpperCase()+ key.substring(1);
      try {
        getMethod = sameClass.getMethod("get" + key);
      }catch (NoSuchMethodException e){
        continue;
      }
      keyType=f.getType();
      if(isEqualType(keyType)){
        try {
          Object targetVal = getMethod.invoke(target);
          if (targetVal != null && !targetVal.equals(getMethod.invoke(origin))) return false;
        }catch (Exception e){
          //continue;
        }
      }
    }
    return true;
  }

  public static boolean isEqualType(Class<?> classType){
    try {
      classType.getMethod("equals",Object.class);
      return true;
    }catch (Exception e){
      return false;
    }
  }

  /**
   * yoMap(new String[]{"key1","key2",new String[]{"value1","value2"}})
   * @param keys
   * @param values
   * @return Map<String,Object>
   */
  public static Map<String,Object> YoMap(String[] keys, Object[] values){
    Map<String,Object> m=new HashMap<String,Object>();
    for(int i=0,len=keys.length;i<len;i++){
      m.put(keys[i],values[i]);
    }
    return m;
  }

  public static <T> String toJson(T entity){
    ObjectMapper objectMapper=new ObjectMapper();
    try {
      return objectMapper.writer().writeValueAsString(entity);
    }catch (Exception e){
      return "";
    }
  }

  public static <T> T loadJson(String json,Class<T> classSource){
    ObjectMapper objectMapper=new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    try{
      return objectMapper.readValue(json,classSource);
    }catch (Exception e){
      return null;
    }
  }
  public static String resolveHumpKey(String prefix,String key){
    return prefix+ key.substring(0,1).toUpperCase()+key.substring(1);
  }
  public static void assign(Object b,Object a){
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
