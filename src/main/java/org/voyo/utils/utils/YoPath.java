package org.voyo.utils.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class YoPath {
  public static String resolvePath(String path,String path2){
    path=path.endsWith("/")?path.substring(0,path.length()-1):path;
    path2=path2.startsWith("/")?path2:"/"+path2;
    String p= path+path2;
    return p.startsWith("/")?p:"/"+p;
  }
  //Relative combine Always.
  public static String resolveUrl(String url,String path){
    url=url.endsWith("/")?url.substring(0,url.length()-1):url;
    path=path.startsWith("/")?path:"/"+path;
    return url+path;
  }
}
