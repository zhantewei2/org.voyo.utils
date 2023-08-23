package org.voyo.utils.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class YoStr {
    public YoStr() {
    }

    public static boolean isBlank(String str){
        return str==null||"".equals(str);
    }

    public static boolean isAnyBlank(String... items){
        if(items==null ) return true;
        for(String i:items){
            if(isBlank(i)) return true;
        }
        return false;
    }

    public static String padsLeft(int count, String str, String padPer) {
        return padsLeft(count, str, padPer.charAt(0));
    }

    public static String padsLeft(int count, String str, char padChar) {
        int padsCount = count - str.length();
        if (padsCount <= 0) {
            return str;
        } else {
            char[] chars;
            for(chars = new char[padsCount]; padsCount-- > 0; chars[padsCount] = padChar) {
            }

            return new String(chars) + str;
        }
    }

    public static String padsRight(int count, String str, String padPer) {
        return padsRight(count, str, padPer.charAt(0));
    }

    public static String padsRight(int count, String str, char padChar) {
        int padsCount = count - str.length();
        if (padsCount <= 0) {
            return str;
        } else {
            char[] chars;
            for(chars = new char[padsCount]; padsCount-- > 0; chars[padsCount] = padChar) {
            }

            return str + new String(chars);
        }
    }

    public boolean equals(String a, String b) {
        return a != null && a.equals(b);
    }

    public static List<String> toList(String listStr,String segment){
        String[] a=listStr.split(segment);
        return a.length>0 ? Arrays.asList(a): null;
    }

    public static List<Long> toListLong(String listStr,String segment){
        String[] a=listStr.split(segment);
        return a.length>0?
                Arrays.stream(a).map(Long::valueOf).collect(Collectors.toList()):
                null;
    }
}