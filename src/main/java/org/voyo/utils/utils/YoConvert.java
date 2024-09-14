package org.voyo.utils.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

public class YoConvert {

    public static String toStr(Number v){
        return v==null?null:String.valueOf(v);
    }

    public static Integer toInt(String v){
        if(YoStr.isBlank(v))return null;
        v=v.replace(",","");
        try {
            return Integer.valueOf(v);
        }catch (Exception ignore){
            return null;
        }
    }
    public static Integer toInt(Number v){
        if(v==null)return null;
        return v.intValue();
    }

    public static Double toDouble(String v){
        if(YoStr.isBlank(v))return null;

        v=v.replace(",","");
        try {
            return Double.valueOf(v);
        }catch (Exception ignore){
            return null;
        }
    }
    public static BigDecimal toBigDecimal(String v){
        if(YoStr.isBlank(v))return null;
        v=v.replace(",","");
        try {
            return new BigDecimal(v);
        }catch (Exception ignore){
            return null;
        }
    }

}
