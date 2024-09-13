package org.voyo.utils.utils.copy;

import lombok.extern.slf4j.Slf4j;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class YoCopyUtil {
  public static volatile HashMap<String, Map<String, MethodHandle[]>> cache=new HashMap<>();

  public static String getGetKey(String key){
    return "get"+key.substring(0,1).toUpperCase()+key.substring(1);
  }
  public static String getSetKey(String key){
    return "set"+key.substring(0,1).toUpperCase()+key.substring(1);
  }

  public static <T,S> void copy(T source,S target){
    Class<?> sourceType=source.getClass();
    Class<?> targetType=target.getClass();
    Map<String,MethodHandle[]> cacheSource=cacheClazz(sourceType);
    Map<String,MethodHandle[]> cacheTarget=cacheClazz(targetType);
    MethodHandle sourceGetMethod= null;
    MethodHandle targetSetMethod= null;
    for(String key:cacheSource.keySet()){
      MethodHandle[] targetHandles= cacheTarget.get(key);
      MethodHandle[] sourceHandles= cacheSource.get(key);
      if(targetHandles==null) continue;
      sourceGetMethod= sourceHandles[0];
      targetSetMethod= targetHandles[1];
      if(sourceGetMethod == null || targetSetMethod ==null) continue;
      try {
        targetSetMethod.invoke(target, sourceGetMethod.invoke(source));
      }catch (Throwable e){
        // ignore..
      }
    }

  }

  public static Map<String,MethodHandle[]> cacheClazz(Class<?> type){
    String key=type.getTypeName();
    Map<String,MethodHandle[]> m=cache.get(key);
    if(m!=null) return m;
    m=new HashMap<>();
    MethodHandles.Lookup lookup=MethodHandles.lookup();
    String name=null;
    for(Field f: type.getDeclaredFields()){
      name=f.getName();
      MethodHandle[] handles=new MethodHandle[2];
      try {
        final MethodHandle getM = lookup.findVirtual(type, getGetKey(name), MethodType.methodType(f.getType()));
        handles[0]=getM;
      }catch (NoSuchMethodException |IllegalAccessException e){
        log.error("Failure invoke get",e);
        e.printStackTrace();
        handles[0]=null;
      }
      try{
        final MethodHandle setM= lookup.findVirtual(type, getSetKey(name),MethodType.methodType(void.class,f.getType()));
        handles[1]=setM;
      }catch (NoSuchMethodException| IllegalAccessException e){
        log.error("Failure invoke set",e);
        handles[1]=null;
      }
      m.put(name, handles);
    }
    cache.put(key,m);
    return m;
  }
}
