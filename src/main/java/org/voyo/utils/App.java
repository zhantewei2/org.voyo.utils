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
    AA aa=new AA();
    System.out.println("app:"+App.class.getTypeName());
  }


}
