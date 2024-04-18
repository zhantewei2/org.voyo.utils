package org.voyo.utils;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import lombok.Getter;
import lombok.Setter;
import org.voyo.utils.jackson.LikeCharacterFieldDeserializer;
import org.voyo.utils.jackson.TrimFieldDeserializer;
import org.voyo.utils.jackson.format.LikeCharacter;
import org.voyo.utils.jackson.format.Trim;
import org.voyo.utils.utils.YoList;
import org.voyo.utils.utils.YoObject;
import org.voyo.utils.utils.concurrent.YoConcurrent;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    @Setter
    @Getter
    public static class Tt{
        @LikeCharacter
        private String name;
        private String name2;
        Tt(String name,String name2){
            this.name=name;
            this.name2=name2;
        }
    }

    public static void main(String[] args) throws Exception{
        List<Tt> tt=new ArrayList<>();

        tt.add(new Tt("2x","b"));
        tt.add(new Tt("b","c"));
        tt.add(new Tt("x","x"));

        Map<String,String> m= YoList.toMap(tt,Tt::getName,Tt::getName2);
        System.out.println(m);
    }


}
