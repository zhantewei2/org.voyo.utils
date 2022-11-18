package org.voyo.utils.test;

import java.util.concurrent.FutureTask;

public class Run {
  public static void main(String[] args) throws Exception{
//    ThreadDemo threadDemo=new ThreadDemo();
//    threadDemo.begin("name1");
//
//    ThreadDemo threadDemo1=new ThreadDemo();
//    threadDemo1.begin("name2");


    Thread t=new Thread(new FutureTask<>(new ThreadCallable()));
    Thread t2=new Thread(new FutureTask<>(new ThreadCallable()));
    Thread t3=new Thread(new FutureTask<>(new ThreadCallable()));

    t2.start();
    t3.start();
//    t.setDaemon(true);
//    t.interrupt();
    t.start();
//    t.join();
    t.join();
    t2.join();
    t3.join();

    System.out.println("end");
  }
}
