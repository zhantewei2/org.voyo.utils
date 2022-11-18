package org.voyo.utils.utils;


import java.util.*;

public class YoMap {
  public static String getStr(Map<String,Object> m,String key){
    Object val= m.get(key);
    return val!=null? (String)val: null;
  }
  public static Integer getInt(Map<String,Object>m,String key){
    Object val= m.get(key);
    return val!=null? Integer.valueOf((String)val): null;
  }

  public static Long getLong(Map<String,Object> m ,String key){
    Object val=m.get(key);
    return val!=null? Long.valueOf((String)val):null;
  }

  public static void main(String[] args) {
    String a= null;
    String b="x";
    System.out.println(String.format("xxxx %s - %s ",b,a));
  }
}
