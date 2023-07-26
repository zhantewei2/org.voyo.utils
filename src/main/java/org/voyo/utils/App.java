package org.voyo.utils;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.voyo.utils.utils.YoFormUpload;
import org.voyo.utils.utils.YoIO;
import java.io.FileInputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class App {


    @SneakyThrows
  public static void main(String[] args){
      MethodHandles.Lookup lookup= MethodHandles.lookup();

      AA aa=new AA();
      aa.setName("name1");
      aa.setAge(12);
      Class<AA> aaClass=AA.class;
      Field[] fields= aaClass.getDeclaredFields();

      for(Field f:fields){
          String name=f.getName();
          String methodName="get"+name.substring(0,1).toUpperCase()+name.substring(1);
          MethodType methodType=MethodType.methodType(f.getType());
          MethodHandle getMethod= lookup.findVirtual(aaClass,methodName,methodType);
          Object r=getMethod.invoke(aa);
          System.out.println(r);
      }
  }


  @Data
  static class AA{
      public String name;
      private Integer age;

      public void methodOne(){}
  }



}
