package org.voyo.utils.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class YoList {
    public static String longToString(List<Long> ids,String sep){
        StringBuilder stringBuilder=new StringBuilder();
        ids.forEach(id->{
            stringBuilder.append(String.valueOf(id)).append(sep);
        });
        String content= stringBuilder.toString();
        return content.substring(0,content.length()-1);
    }
    public static List<Long> longFromString(String content,String sep){
        return Arrays
                .stream(content.split(sep))
                .filter(i->i!=null&&!"".equals(i))
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }
    public static List<Long> longFromString(String content){
        return longFromString(content,",");
    }

    public static String longToStringDefault(List<Long> ids){
        return longToString(ids,",");
    }

    public static String toString(List<String> ids,String sep){
        return String.join(sep,ids);
    }
    public static List<String> fromString(String content,String sep){
        return Arrays
                .stream(content.split(sep))
                .filter(i->i!=null&!"".equals(i))
                .collect(Collectors.toList());
    }
    public static List<String> fromString(String content){
        return fromString(content,",");
    }

    public static String toStringDefault(List<String> ids){
        return toString(ids,",");
    }

    public static <T> T findFirst(List<T> list,Function<T,Boolean> run){
        Iterator<T> iterator= list.stream().iterator();
        while(iterator.hasNext()){
            T i= iterator.next();
            if(run.apply(i)) {
                return i;
            }
        }
        return null;
    }

    public boolean isEmpty(List<Object> list){
        if(list==null)return true;
        return list.size()<1;
    }
}
