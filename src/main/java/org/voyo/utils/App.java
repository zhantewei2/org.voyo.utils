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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class App {


    @SneakyThrows
  public static void main(String[] args){
        Pattern p= Pattern.compile("[^\\/]+?(?=(\\?|#|$))");
        Matcher m=p.matcher("https://www.baidu.com/abc.jpg?sx=1#1");
        if(m.find()){
            System.out.println("group"+m.group());
        }else{

        }
  }


  @Data
  static class AA{
      public String name;
      private Integer age;

      public void methodOne(){}
  }



}
