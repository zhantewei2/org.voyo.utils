package org.voyo.utils.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class YoDate {
  public static String BASE_DATE_FORMAT="yyyy-MM-dd";
  public static String BASE_TIME_FORMAT= "yyyy-MM-dd HH:mm:ss";

  public static Date parseDateStr(String str,String format) throws ParseException{
    return parse(str,StringUtils.isEmpty(format)?BASE_DATE_FORMAT:format);
  }
  public static Date parseTimeStr(String str,String format) throws ParseException{
    return parse(str,StringUtils.isEmpty(format)?BASE_TIME_FORMAT:format);
  }
  public static String formatDate(Date date,String format){
    return format(date,StringUtils.isEmpty(format)?BASE_DATE_FORMAT:format);
  }
  public static String formatTime(Date date,String format){
    return format(date,StringUtils.isEmpty(format)?BASE_TIME_FORMAT:format);
  }

  public static Date parse(String str,String format) throws ParseException {
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
    return simpleDateFormat.parse(str);
  }
  public static String format(Date date,String format){
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
    return simpleDateFormat.format(date);
  }
}
