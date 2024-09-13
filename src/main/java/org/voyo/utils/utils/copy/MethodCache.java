package org.voyo.utils.utils.copy;

import java.lang.invoke.MethodHandle;

public class MethodCache{
  private final MethodHandle getMethod;
  private final MethodHandle setMethod;

  public MethodCache(MethodHandle getMethod,MethodHandle setMethod){
    this.getMethod=getMethod;
    this.setMethod=setMethod;
  }
  public MethodHandle getGetMethod(){
    return getMethod;
  }
  public MethodHandle getSetMethod(){
    return setMethod;
  }
}