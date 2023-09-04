package org.voyo.utils;

import org.voyo.utils.utils.YoDate;
import org.voyo.utils.utils.url.YoUrl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class App {
    public static void main(String[] args){
        String url="https://blog.csdn.net/weixin_42501688/article/details/114617205?name=1&&age=2#wechat";
        String url2=YoUrl.additionUrl(url,new HashMap<String,Object>(){{
            put("ztwx","http://www.163.com");
        }});
        System.out.println(url2);
        System.out.println(YoUrl.parse(url2).getQuery());

    }
}
