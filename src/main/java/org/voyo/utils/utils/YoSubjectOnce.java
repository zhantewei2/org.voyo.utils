package org.voyo.utils.utils;

import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public class YoSubjectOnce<T> {
    private volatile T info;
    private volatile Exception globalErr;

    public T syncAwait(long maxTimestamp) throws Exception{
        return syncAwait(maxTimestamp,10);
    }

    public T syncAwait(long maxTimestamp,long sleepTime) throws Exception {
        if(info!=null) return info;
        int total= (int) Math.floor((double) maxTimestamp /sleepTime);
        int index=0;
        while(true){
            index++;
            try {
                Thread.sleep(sleepTime);
            }catch (Exception e){}
            if(index>=total) throw new TimeoutException("yoSubject sync await timeout");
            if(globalErr!=null) throw globalErr;
            if(info!=null) return info;
        }
    }

    public void end(T info){
        this.info=info;
    }
    public void error(Exception e){
        this.globalErr=e;
    }
}

