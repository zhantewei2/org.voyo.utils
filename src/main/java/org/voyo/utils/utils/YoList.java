package org.voyo.utils.utils;

import org.voyo.utils.params.Tuple2;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class YoList {

    public static String longToString(List<Long> ids, String sep) {
        StringBuilder stringBuilder = new StringBuilder();
        ids.forEach((id) -> {
            stringBuilder.append(String.valueOf(id)).append(sep);
        });
        String content = stringBuilder.toString();
        return content.substring(0, content.length() - 1);
    }

    public static List<Long> longFromString(String content, String sep) {
        return Arrays.stream(content.split(sep)).filter((i) -> {
            return i != null && !"".equals(i);
        }).map(Long::valueOf).collect(Collectors.toList());
    }

    public static List<Long> longFromString(String content) {
        return longFromString(content, ",");
    }

    public static String longToStringDefault(List<Long> ids) {
        return longToString(ids, ",");
    }

    public static String toString(List<String> ids, String sep) {
        return String.join(sep, ids);
    }

    public static List<String> fromString(String content, String sep) {
        return Arrays.stream(content.split(sep)).filter((i) -> {
            return i != null & !"".equals(i);
        }).collect(Collectors.toList());
    }

    public static List<String> fromString(String content) {
        return fromString(content, ",");
    }

    public static String toStringDefault(List<String> ids) {
        return toString(ids, ",");
    }

    public static <T> T findFirst(List<T> list, Function<T, Boolean> run) {
        Iterator<T> iterator = list.stream().iterator();
        while(iterator.hasNext()){
            T i= iterator.next();
            if(run.apply(i)) {
                return i;
            }
        }
        return null;
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list==null || list.isEmpty();
    }

    public static <T> void partitionRun(Collection<T> collection,int partitionSize,Consumer<List<T>> consumer){
        List<T> partList=new ArrayList<>();
        int index=0;
        Iterator<T> iterator=collection.stream().iterator();
        while(true){
            index++;
            if(iterator.hasNext()){
                partList.add(iterator.next());
                if(index>=partitionSize){
                    consumer.accept(partList);
                    partList.clear();
                    index=0;
                }
            }else{
                if(!partList.isEmpty()){
                    consumer.accept(partList);
                    partList.clear();
                }
                break;
            }

        }
    }

    public static <T,K,V> Map<K,V> toMap(Collection<T> list, Function<T,K> getKey, Function<T,V> getVal){
        Map<K,V> m=new HashMap<>();
        for(T i:list){
            m.putIfAbsent(getKey.apply(i), getVal.apply(i));
        }
        return m;
    }


    public static <T,S> List<T> notIn(List<T> originList, List<S> dataList, BiPredicate<T,S> predicate){
        return originList.stream().filter(i->{
            return YoList.<S>findFirst(dataList,j->predicate.test(i,j)) == null;
        }).collect(Collectors.toList());
    }

    public static <T,S> List<T> in(List<T> originList,List<S> dataList, BiPredicate<T,S> predicate){
        return originList.stream().filter(i->{
            return YoList.<S>findFirst(dataList,j->predicate.test(i,j))!= null;
        }).collect(Collectors.toList());
    }

    public static <T,S> Tuple2<List<T>,List<T>> notInAndIn(
            List<T> originList,
            List<S> dataList,
            BiPredicate<T,S> predicateNotIn,
            BiPredicate<T,S> predicateIn
    ){
        List<T> notInList=new ArrayList<>();
        List<T> inList=new ArrayList<>();
        originList.forEach(i->{
            if(YoList.<S>findFirst(dataList,j->predicateNotIn.test(i,j)) == null) notInList.add(i);
            if(YoList.<S>findFirst(dataList,j->predicateIn.test(i,j))!=null ) inList.add(i);
        });
        return new Tuple2(notInList,inList);
    }
}
