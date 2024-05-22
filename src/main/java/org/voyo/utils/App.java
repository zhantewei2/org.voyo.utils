package org.voyo.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.voyo.utils.utils.invoke.YoInvoke;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class App {
    public static class Tt {

        private String name;
        private Integer age;

        private Integer age1;

        private Integer age2;
        public String getName(){
            return this.name;
        }
        public void setName(String name){
            this.name=name;
        }
        public Integer getAge(){
            return age;
        }
        public void setAge(Integer age){
            this.age=age;
        }
        public Integer getAge1(){
            return age;
        }
        public void setAge1(Integer age){
            this.age=age;
        }
        public Integer getAge2(){
            return age;
        }
        public void setAge2(Integer age){
            this.age=age;
        }

    }

    public static <T> void main(String[] args) throws Exception{
        run3();
        run2();
        run1();
    }

    public static void run2(){
        int total=10000;
        long start=System.currentTimeMillis();
        while(total>0) {
            Tt t1 = new Tt();
            Tt t2 = new Tt();
            t1.setAge(1);
            t1.setName("a");
            t2.setName("b");
            t2.setAge(2);
            t1.setAge(t2.getAge());
            t1.setName(t2.getName());
            total--;
        }
        System.out.println("complete, duration:"+ String.valueOf(System.currentTimeMillis()-start));
    }
    public static void run1(){
        int total=10000;
        YoInvoke<Tt> yoInvoke = new YoInvoke<>(Tt.class);
        long start=System.currentTimeMillis();
        while(total>0) {
            Tt t1 = new Tt();
            Tt t2 = new Tt();
            t1.setAge(1);
            t1.setName("a");
            t2.setName("b");
            t2.setAge(2);
            yoInvoke.assign(t1, t2);
            total--;
        }
        System.out.println("complete, duration:"+ String.valueOf(System.currentTimeMillis()-start));
    }
    public static void run3(){
        int total=10000;
        long start=System.currentTimeMillis();
        while(total>0) {
            Tt t1 = new Tt();
            Tt t2 = new Tt();
            t1.setAge(1);
            t1.setName("a");
            t2.setName("b");
            t2.setAge(2);
            assign(t1,t2);
            total--;
        }
        System.out.println("complete, duration:"+ String.valueOf(System.currentTimeMillis()-start));
    }
    public static String resolveHumpKey(String prefix, String key) {
        return prefix + key.substring(0, 1).toUpperCase() + key.substring(1);
    }

    public static void assign(Object b, Object a) {
        Class<?> aClass=a.getClass();
        Class<?> bClass=b.getClass();
        Field[] afs=aClass.getDeclaredFields();
        Field[] bfs=bClass.getDeclaredFields();
        String key;
        String getKey;
        String setKey;
        Method getMethod;
        Method setMethod;
        for(Field field:afs){
            key=field.getName();
            getKey=resolveHumpKey("get",key);
            setKey=resolveHumpKey("set",key);
            try {
                getMethod= aClass.getMethod(getKey);
                setMethod= bClass.getMethod(setKey,field.getType());
            }catch (Exception e){
                continue;
            }
            try {
                setMethod.invoke(b,getMethod.invoke(a));
            }catch (Exception e){}
        }

    }
}
