package org.voyo.utils;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import lombok.Getter;
import lombok.Setter;
import org.voyo.utils.jackson.LikeCharacterFieldDeserializer;
import org.voyo.utils.jackson.format.LikeCharacter;
import org.voyo.utils.utils.YoConvert;
import org.voyo.utils.utils.YoDate;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


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
    public void run() throws Exception{}

    public static void main(String[] args) throws Exception{
        Integer b=1;
        System.out.println(YoConvert.toBigDecimal("12.4"));
    }


}
