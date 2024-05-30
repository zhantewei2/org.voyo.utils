package org.voyo.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.voyo.utils.utils.YoList;
import org.voyo.utils.utils.YoObject;
import org.voyo.utils.utils.invoke.YoInvoke;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        public Tt(String name,Integer age){
            this.name=name;
            this.age=age;
        }

    }

    public static <T> void main(String[] args) throws Exception{
        List<Tt> list1=new ArrayList<>();
        list1.add(new Tt("a",1));
        list1.add(new Tt("b",2));

        List<String> b= Arrays.asList("a","c");


    }


}
