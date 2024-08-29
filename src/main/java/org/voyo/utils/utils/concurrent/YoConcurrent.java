package org.voyo.utils.utils.concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class YoConcurrent {
    /**
     * concurrent run task.
     * 一次错误，会关闭一个线程。 出现concurrentPoolSize次错误后，所有线程均关闭
     * @param list
     * @param concurrentSize
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
                        break;
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
