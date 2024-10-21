package org.voyo.utils;


import org.voyo.utils.utils.YoList;
import org.voyo.utils.utils.YoReflect;
import lombok.extern.slf4j.Slf4j;
import org.voyo.utils.utils.concurrent.YoConcurrent;
import org.voyo.utils.utils.url.UrlNode;
import org.voyo.utils.utils.url.YoUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    List<Integer> list=new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    YoConcurrent.concurrentRun(list,1,i->{
      log.info("i:{}",i);
    });
  }


}
