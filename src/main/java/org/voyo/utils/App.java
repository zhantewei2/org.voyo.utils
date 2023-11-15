package org.voyo.utils;

import lombok.SneakyThrows;
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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception{
        List<Integer> arr=Arrays.asList(5,6,7,1,2,3,4);
        List<String> arr2=arr.stream().sorted((a,b)->a-b).map(i->String.valueOf(i)).collect(Collectors.toList());
        System.out.println(String.join(":",arr2.toArray(new String[arr2.size()])));
    }

}
