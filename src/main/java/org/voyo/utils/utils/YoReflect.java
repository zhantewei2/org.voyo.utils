package org.voyo.utils.utils;

import lombok.extern.slf4j.Slf4j;
import org.voyo.utils.utils.copy.MethodCache;
import org.voyo.utils.utils.copy.YoCopyUtil;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class YoReflect<T> {
  private final HashMap<String, MethodCache> cache=new HashMap<>();
  private final MethodHandles.Lookup lookup = MethodHandles.lookup();

  private MethodHandle getGetMethod(Class<T> type,Field f){
    String key=f.getName();
    try {
      return lookup.findVirtual(type, YoCopyUtil.getGetKey(key), MethodType.methodType(f.getType()));
    }catch (NoSuchMethodException | IllegalAccessException e) {
      return null;
    }
  }
  private MethodHandle getSetMethod(Class<T> type,Field f){
    String key=f.getName();
    try {
      return lookup.findVirtual(
          type,
          YoCopyUtil.getSetKey(key),
          MethodType.methodType(void.class,f.getType()));
    }catch (NoSuchMethodException | IllegalAccessException e) {
      return null;
    }
  }

  private void cacheKeys(Class<T> type){
    Field[] fields=type.getDeclaredFields();
    for(Field f:fields){
      String key=f.getName();
      MethodCache mc=new MethodCache(
          getGetMethod(type,f),
          getSetMethod(type,f)
      );
      cache.put(key,mc);
    }
  }


  public YoReflect(Class<T> type){
    cacheKeys(type);
  }
  public <S> S invokeGet(T target,String key){
    try {
      return (S)cache.get(key).getGetMethod().invoke(target);
    }catch (Throwable e){
      return null;
    }
  }

  public <S> void invokeSet(T target,String key,S v){
    try{
      cache.get(key).getSetMethod().invoke(target,v);
    }catch (Throwable e){

    }
  }
  public void copy(T source, T target, List<String> excludeKeys){
    for(String key:cache.keySet()){
      if(excludeKeys.contains(key)) continue;

      MethodCache methodCache=cache.get(key);
      try {
        methodCache.getSetMethod().invoke(
            target,
            methodCache.getGetMethod().invoke(
                source
            )
        );
      }catch (Throwable e){
        // ignore..
      }
    }
  }
  public void copy(T source,T target){
    copy(source,target,new ArrayList<>());
  }

  /**
   * compare two object.
   * if property of newObj is null, means no update is required.
   */
  public boolean shouldNotUpdate(T oldObj,T newObj,List<String> excludeKeys){
    MethodCache methodCache=null;
    MethodHandle getMethod=null;
    for(String key:cache.keySet()){
      if(excludeKeys.contains(key))continue;
      methodCache=cache.get(key);
      getMethod=methodCache.getGetMethod();
      try {
        Object oldVal = getMethod.invoke(oldObj);
        Object newVal = getMethod.invoke(newObj);
        if(newVal!=null && !newVal.equals(oldVal)) return false;
      }catch (Throwable e){
        continue;
      }
    }
    return true;
  }
  public boolean shouldNotUpdate(T oldObj,T newObj){
    return shouldNotUpdate(oldObj,newObj,new ArrayList<>());
  }

  /**
   * compare two object
   * if property of newObj is null, but oldObj is not null, means is not same.
   */
  public boolean isSame(T oldObj,T newObj,List<String> excludeKeys){
    MethodCache methodCache=null;
    MethodHandle getMethod=null;
    for(String key:cache.keySet()){
      if(excludeKeys.contains(key))continue;
      methodCache=cache.get(key);
      getMethod=methodCache.getGetMethod();
      try {
        Object oldVal = getMethod.invoke(oldObj);
        Object newVal = getMethod.invoke(newObj);
        if(newVal==null && oldVal!=null) return false;
        if(newVal!=null && !newVal.equals(oldVal)) return false;
      }catch (Throwable e){
        continue;
      }
    }
    return true;
  }
  public boolean isSame(T oldObj,T newObj){
    return isSame(oldObj,newObj,new ArrayList<>());
  }

  public List<Object> getAll(T obj,String[] keys){
    List<Object> result=new ArrayList<>(keys.length);
    MethodHandle m=null;
    for(int i=0,len=keys.length;i<len;i++){
      m=cache.get(keys[i]).getGetMethod();
      try {
        result.add(m.invoke(obj));
      }catch (Throwable e){
        result.add(null);
      }
    }
    return result;
  }
}
