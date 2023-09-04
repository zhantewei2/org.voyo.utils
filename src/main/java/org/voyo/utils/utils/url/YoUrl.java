package org.voyo.utils.utils.url;

import lombok.extern.slf4j.Slf4j;
import org.voyo.utils.utils.YoStr;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class YoUrl {

  private static final Pattern pureUrlPattern= Pattern.compile("^[^#?]*");
  private static final Pattern queryPattern=Pattern.compile("(?<=\\?)[^#?]*");
  private static final Pattern tagPattern=Pattern.compile("(?<=#)[^#?]*");

  private static final String urlEncodeEnc="UTF-8";

  /*
    解析url, 并返回
    pureUrl
    query
    tag
   */
  public static UrlNode parse(String url){
    if(YoStr.isBlank(url)) return new UrlNode();
    Matcher queryM=queryPattern.matcher(url);
    Matcher pureM=pureUrlPattern.matcher(url);
    Matcher tagM=tagPattern.matcher(url);
    UrlNode urlNode=new UrlNode();
    if(pureM.find()){
      urlNode.setPureUrl(pureM.group());
    }else{
      urlNode.setPureUrl("");
    }
    if(queryM.find()){
      try {
        urlNode.setQuery(decodeQuery(queryM.group()));
      }catch (UnsupportedEncodingException e){
        urlNode.setQuery(new HashMap<>());
      }
    }else{
      urlNode.setQuery(new HashMap<>());
    }
    if(tagM.find()){
      urlNode.setTag(tagM.group());
    }else{
      urlNode.setTag("");
    }

    return urlNode;
  }

  /**
   * 解析queryStr为 map。 此处queryStr不可为“?”开头
   * @param queryStr
   * @return
   * @throws UnsupportedEncodingException
   */
  public static Map<String,Object> decodeQuery(String queryStr) throws UnsupportedEncodingException {
    Map<String,Object>  m=new HashMap<>();
    if(YoStr.isBlank(queryStr)) return m;
    String[] splitArr=queryStr.split("&");
    String entryVal=null;
    for(String v:splitArr){
      String[] entryArr=v.split("=");
      m.put(
        entryArr[0],
        entryArr.length>1?
          URLDecoder.decode(entryArr[1],urlEncodeEnc)
          :null);
    }
    return m;
  }

  public static String encodeQuery(Map<String,Object> query){
    StringBuilder builder=new StringBuilder();
    String value;
    String key;
    for(Map.Entry<String,Object> entry:query.entrySet()){
      key=entry.getKey();
      if(YoStr.isBlank(key)) continue;
      value=entry.getValue()!=null?(String) entry.getValue(): "";
      System.out.println("key:"+entry.getKey());
      try {
        value = URLEncoder.encode(value, urlEncodeEnc);
      }catch (UnsupportedEncodingException e){
        value = "";
      }
      builder.append(String.format("%s=%s&",key,value));
    }
    builder.deleteCharAt(builder.length()-1);
    return builder.toString();
  }

  /**
   * 为url添加新query参数，原query与tag仍保留。
   * @param url
   * @param additionQuery
   * @return
   */
  public static String additionUrl(String url,Map<String,Object> additionQuery){
    UrlNode urlNode=parse(url);
    StringBuilder stringBuilder=new StringBuilder();
    stringBuilder.append(urlNode.getPureUrl());
    Map<String,Object> query=urlNode.getQuery();
    query.putAll(additionQuery);
    String queryStr=encodeQuery(query);
    if(!YoStr.isBlank(queryStr)) stringBuilder.append("?").append(queryStr);
    if(!YoStr.isBlank(urlNode.getTag())) stringBuilder.append("#").append(urlNode.getTag());
    return stringBuilder.toString();

  }
}
