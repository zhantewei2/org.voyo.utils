package org.voyo.utils;


import org.voyo.utils.utils.YoReflect;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
  }


}
