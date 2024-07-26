package org.voyo.utils.utils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

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

  public static String queryEncode(Map<String,Object> m){
    StringBuilder stringBuilder=new StringBuilder();

    m.entrySet().forEach((e)->{
      String val;
      try {
        val = URLEncoder.encode(String.valueOf(e.getValue()),"UTF-8");
      }catch (UnsupportedEncodingException err){
        val = "";
      }
      stringBuilder.append(e.getKey()+"="+val+"&");
    });
    String content=stringBuilder.toString();
    return content.substring(0,content.length()-1);
  }

  public static String getSuffix(String fileName){
    if(YoStr.isBlank(fileName))return "";
    int lastIndex=fileName.lastIndexOf(".");
    System.out.println(lastIndex);
    return lastIndex<0?"":fileName.substring(lastIndex+1);
  }
}
