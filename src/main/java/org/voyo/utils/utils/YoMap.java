package org.voyo.utils.utils;


import java.util.*;

public class YoMap {

  public static class MapNode<T,S>{
      private HashMap<T,S> map=new HashMap<>();
      public MapNode<T,S> put(T k,S v){
        map.put(k,v);
        return this;
      }
      public HashMap<T,S> get(){
        return this.map;
      }
  }
  public static String getStr(Map<String, Object> m, String key) {
    Object val = m.get(key);
    return val != null ? String.valueOf(val) : null;
  }

  public static Integer getInt(Map<String, Object> m, String key) {
    Object val = m.get(key);
    return val != null ? Integer.valueOf(String.valueOf(val)) : null;
  }

  public static Long getLong(Map<String, Object> m, String key) {
    Object val = m.get(key);
    return val != null ? Long.valueOf(String.valueOf(val)) : null;
  }


  public static <S> MapNode<String,S> buildMap(){
    return new MapNode<String,S>();
  }
  public static void main(String[] args) {}
}
