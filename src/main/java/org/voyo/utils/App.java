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
import org.voyo.utils.utils.YoStr;
import org.voyo.utils.utils.concurrent.YoConcurrent;
import java.util.*;
import java.util.regex.Pattern;
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

    public void run(){}
    public static void main(String[] args) throws Exception{

        System.out.println(YoStr.underlineKeyToHump("xx_a_cc"));
    }


}
