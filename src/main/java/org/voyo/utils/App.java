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
        Integer threadCount=2;
        ExecutorService pool= Executors.newFixedThreadPool(threadCount);

        List<Callable<Void>> queue=new ArrayList<>();
        List<String> a=new ArrayList<>();
        System.out.println(System.identityHashCode(a));
        for(int i=0;i<threadCount;i++) {
            queue.add(() -> {
                System.out.println(System.identityHashCode(a));
                return null;
            });
        }
        pool.invokeAll(queue);
    }

}
