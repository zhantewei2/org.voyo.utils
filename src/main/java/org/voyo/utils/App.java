package org.voyo.utils;

import org.voyo.utils.utils.UniqueId;

import java.util.*;


public class App {
  public static void main(String[] args)throws Exception{
    Integer count=10000;
    while(count-->0){
      System.out.println(UniqueId.getUId());
    }
  }
}
