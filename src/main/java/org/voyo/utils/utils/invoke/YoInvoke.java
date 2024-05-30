package org.voyo.utils.utils.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YoInvoke<T> {
    private final Map<String,MethodHandle> getMethods=new HashMap<>();
    private final Map<String,MethodHandle> setMethods=new HashMap<>();
    private final List<String> keys=new ArrayList<>();

    private final Class<T> clsOrigin;

    private String resolveHumpKey(String prefix, String key) {
        return prefix + key.substring(0, 1).toUpperCase() + key.substring(1);
    }
    public YoInvoke(Class<T> cls){
        MethodHandles.Lookup lookup= MethodHandles.lookup();
        clsOrigin=cls;
        for(Field field:cls.getDeclaredFields()){
            String key=field.getName();
            String getName=resolveHumpKey("get",key);
            String setName=resolveHumpKey("set",key);
            try {
                MethodHandle methodHandle=lookup.findVirtual(cls, getName, MethodType.methodType(field.getType()));
                MethodHandle setMethodHandle=lookup.findVirtual(cls,setName,MethodType.methodType(void.class,field.getType()));
                getMethods.put(key,methodHandle);
                setMethods.put(key,setMethodHandle);
            }catch (NoSuchMethodException|IllegalAccessException e){
                e.printStackTrace();
                continue;
            }
            keys.add(key);
        }
    }

    public T assign(T target,T attachment){
        for(String key : keys){
            final MethodHandle getMethod=getMethods.get(key);
            final MethodHandle setMethod=setMethods.get(key);
            if(getMethod==null||setMethod==null) continue;
            try {
                Object attachmentVal=getMethod.invoke(attachment);
                if(attachmentVal!=null) setMethod.invoke(target,attachmentVal);
            }catch (Throwable e){}
        }
        return target;
    }
}
