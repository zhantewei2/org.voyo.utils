package org.voyo.utils;


import lombok.Data;
import org.voyo.utils.utils.*;

import java.util.Calendar;
import java.util.regex.Pattern;

public class App {
  @Data
  public static class AA {
    private String name;
    private String age;
    private String position;
  }

  @Data
  public static class BB {
    private String name;
    private String age;
  }

  public static <T> void main(String[] args) throws Exception {
    AA aa=new AA();
    aa.setName("name1");
    aa.setAge("AGE1");
    aa.setPosition("POSITION");
    BB bb=new BB();
    YoObject.copy(aa,bb);
    System.out.println(YoObject.toJson(bb));
  }


}
