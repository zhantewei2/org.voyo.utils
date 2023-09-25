package org.voyo.utils;

import org.voyo.utils.utils.UniqueId;
import org.voyo.utils.utils.YoDate;
import org.voyo.utils.utils.YoMap;
import org.voyo.utils.utils.url.YoUrl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args){
        Integer count=10000;
        Set<Long> s=new HashSet<Long>();
        while(count-->0){
            s.add(UniqueId.getUId());
        }
        System.out.println(s.size());
//        System.out.println(s.size());
    }

}
