package org.voyo.utils;


import org.springframework.beans.BeanUtils;
import org.voyo.utils.utils.YoObject;
import org.voyo.utils.utils.YoReflect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

public class App {


  public static class AA{
    private String name;
    private Integer age;
    public void setName(String name) {
      this.name = name;
    }
    public void setAge(Integer age) {
      this.age = age;
    }

    public String getName() {
      return name;
    }
    public Integer getAge() {
      return age;
    }
  }

  public static <T> void main(String[] args) throws Exception {

    int count=100000;
    long startTime=System.currentTimeMillis();
    ttReflect(count);
//    ttInvoke(count);
//    directInvoke(count);
    System.out.println(String.format("duration time: %s",System.currentTimeMillis() - startTime));
  }


  public static void ttReflect(int count){
    YoReflect<AA> yoReflect=new YoReflect<>(AA.class);
    while(count-->0) {
      AA aa = new AA();
      aa.setAge(12);
      aa.setName("name");
      AA bb = new AA();
      BeanUtils.copyProperties(aa, bb);
//      yoReflect.copy(aa,bb);
      bb.setAge(null);
//      System.out.println("isSame:"+yoReflect.shouldNotUpdate(aa,bb));
    }
  }

  public static void ttInvoke(int count){
    while(count-->0){
      AA aa = new AA();
      aa.setAge(12);
      aa.setName("name");
      AA bb = new AA();
      YoObject.assign(bb,aa);
      System.out.println("bb, name:" + bb.getName() + ", age:" + bb.getAge().toString());
    }
  }

  public static void directInvoke(int count){
    while(count-->0){
      AA aa = new AA();
      aa.setAge(12);
      aa.setName("name");
      AA bb = new AA();
      bb.setName(aa.getName());
      bb.setAge(aa.getAge());
      System.out.println("bb, name:" + bb.getName() + ", age:" + bb.getAge().toString());
    }
  }

}
