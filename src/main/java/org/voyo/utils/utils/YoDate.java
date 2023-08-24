package org.voyo.utils.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class YoDate {
  public static String BASE_DATE_FORMAT="yyyy-MM-dd";
  public static String BASE_TIME_FORMAT= "yyyy-MM-dd HH:mm:ss";

  public static Date parseDateStr(String str,String format) throws ParseException{
    return parse(str,StringUtils.isEmpty(format)?BASE_DATE_FORMAT:format);
  }
  public static Date parseDateStr(String str)throws ParseException{
    return parse(str,BASE_DATE_FORMAT);
  }
  public static Date parseTimeStr(String str,String format) throws ParseException{
    return parse(str,StringUtils.isEmpty(format)?BASE_TIME_FORMAT:format);
  }
  public static Date parseTimeStr(String str) throws ParseException{
    return parse(str,BASE_TIME_FORMAT);
  }
  public static String formatDate(Date date,String format){
    return format(date,StringUtils.isEmpty(format)?BASE_DATE_FORMAT:format);
  }

  public static String formatDate(Date date){
    return format(date,BASE_DATE_FORMAT);
  }
  public static String formatTime(Date date,String format){
    return format(date,StringUtils.isEmpty(format)?BASE_TIME_FORMAT:format);
  }

  public static String formatTime(Date date){
    return format(date,BASE_TIME_FORMAT);
  }
  public static Date parse(String str,String format) throws ParseException {
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
    return simpleDateFormat.parse(str);
  }
  public static String format(Date date,String format){
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
    return simpleDateFormat.format(date);
  }

  //获取距当前时间最近的整点时间
  public static Calendar getClosestHourCalendar(){
    Calendar c= Calendar.getInstance();
    int minute=c.get(Calendar.MINUTE);
    if(minute>30) c.add(Calendar.HOUR_OF_DAY,1);
    c.set(Calendar.MINUTE,0);
    c.set(Calendar.SECOND,0);
    return c;
  }

  //获取距当前时间最近的零点时间
  public static Calendar getClosestDayTime(){
    Calendar c=getClosestHourCalendar();
    int hour=c.get(Calendar.HOUR_OF_DAY);
    if(hour>12) c.add(Calendar.DAY_OF_YEAR,1);
    c.set(Calendar.HOUR_OF_DAY,0);
    return c;
  }
  //单位为秒的时间戳，转为Date.
  public static Date secondTimestampToDate(Long timestamp){
    if(timestamp==null)return null;
    Date d=new Date();
    d.setTime(timestamp*1000);
    return d;
  }
  //获得当前的凌晨时间
  public static Calendar getMidnight(Calendar calendar){
    Calendar d=(Calendar) calendar.clone();
    d.set(Calendar.HOUR_OF_DAY,0);
    d.set(Calendar.MINUTE,0);
    d.set(Calendar.SECOND,0);
    return d;
  }
  //获得次日的凌晨时间
  public static Calendar getNextMidnight(Calendar calendar){
    Calendar d=(Calendar) calendar.clone();
    d.set(Calendar.HOUR_OF_DAY,0);
    d.set(Calendar.MINUTE,0);
    d.set(Calendar.SECOND,0);
    d.add(Calendar.DAY_OF_YEAR,1);
    return d;
  }
}
