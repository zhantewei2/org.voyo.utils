package org.voyo.utils.test;

import java.util.concurrent.Callable;

public class ThreadCallable implements Callable<Integer> {
  public Integer call(){
    System.out.println("start thread.");
    try {
      Thread.sleep(1000);
    }catch (Exception e){

    }
    System.out.println("end thread.");
    return 1;
  }
}
