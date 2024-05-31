package org.voyo.utils;


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

    }


}
