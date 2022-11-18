package org.voyo.utils.test;

public class ThreadDemo implements Runnable{
  private String name;
  @Override
  public void run() {
    System.out.println("---runnable start."+name);
    try{
      Thread.sleep(1000);
    }catch (Exception e){
      e.printStackTrace();
    }
    System.out.println("---runnable."+name);
  }


  public void begin(String name){
    Thread t=new Thread(this,"xx");
    this.name=name;
    t.interrupt();
    t.start();

  }
}
