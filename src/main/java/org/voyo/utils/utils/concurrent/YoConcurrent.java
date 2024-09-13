package org.voyo.utils.utils.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
public class YoConcurrent {
    /**
     * concurrent run task.
     * @param list
     * @param concurrentPoolSize
     * @param consumer
     * @param <T>
     */
    public static <T> void concurrentRun(List<T> list, int concurrentPoolSize, Consumer<T> consumer) throws InterruptedException{
        Iterator<T> iterator=list.stream().iterator();
        List<Thread> threads=new ArrayList<>();
        while(concurrentPoolSize-- >0){
            Thread thread=Thread.ofVirtual().start(()->{
                T item=null;
                while(true) {
                    synchronized (list) {
                        item = iterator.hasNext()? iterator.next():null;
                    }
                    if (item == null) break;
                    try {
                        consumer.accept(item);
                    }catch (Exception e){
                        log.error("concurrent error:",e);
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
            threads.add(thread);
        }

        for(Thread thread:threads){
            thread.join();
        }
    }
}
