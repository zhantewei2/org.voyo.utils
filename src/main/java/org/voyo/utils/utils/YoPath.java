package org.voyo.utils.utils;

public class YoPath {
  public static String resolvePath(String path,String path2){
    path=path.endsWith("/")?path.substring(0,path.length()-1):path;
    path2=path2.startsWith("/")?path2:"/"+path2;
    String p= path+path2;
    return p.startsWith("/")?p:"/"+p;
  }
}
