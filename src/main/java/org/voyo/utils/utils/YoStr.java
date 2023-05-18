package org.voyo.utils.utils;

public class YoStr {
    public YoStr() {
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
}