package org.voyo.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

public class App {

  public static <T> void main(String[] args) throws Exception {
    try(
        ExecutorService executorService= Executors.newVirtualThreadPerTaskExecutor()
    ){
      List<Callable<String>> list=new ArrayList<>();
      int count=100;
      while(count-->0){
        int finalCount = count;
        list.add(()->{
          System.out.println("1:"+String.valueOf(finalCount));
          return null;
        });
      }
      executorService.invokeAll(list);
    }
  }


}
