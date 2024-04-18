package org.voyo.utils;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import lombok.Getter;
import lombok.Setter;
import org.voyo.utils.jackson.LikeCharacterFieldDeserializer;
import org.voyo.utils.jackson.TrimFieldDeserializer;
import org.voyo.utils.jackson.format.LikeCharacter;
import org.voyo.utils.jackson.format.Trim;
import org.voyo.utils.utils.concurrent.YoConcurrent;
import java.util.*;

public class App {
    @Setter
    @Getter
    public static class Tt{
        @LikeCharacter
        private String name;
    }

    public static class AA extends JacksonAnnotationIntrospector{
        @Override
        public Object findDeserializer(Annotated a) {
            if(a.hasAnnotation(LikeCharacter.class)){
                System.out.println(12);
                return new LikeCharacterFieldDeserializer();
            }
            if(a.hasAnnotation(Trim.class)){
                return new TrimFieldDeserializer();
            }
            return super.findDeserializer(a);
        }
    }

    public void run(){}

    public void runConcurrent(){
        List<String> list=new ArrayList<>(Arrays.asList("x","b","c","d","e","f","g","h","x","b","c","d","e","f","g","h"));
        try {
            YoConcurrent.concurrentRun(list, 5, item -> {
                try {
                    Thread.sleep(100+(int) (Math.ceil(Math.random() * 500)));
                    System.out.println("item:" + item);
                } catch (Exception e) {

                }
            });
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("end");
    }

    public static void main(String[] args) throws Exception{
        App app=new App();
        app.runConcurrent();

    }


}
