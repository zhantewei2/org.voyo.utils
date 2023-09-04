package org.voyo.utils;

import org.voyo.utils.utils.url.YoUrl;

import java.util.HashMap;

public class App {

    public static void main(String[] args){
        String url2="https://www.baidu.com?x=b#tag";

        String url=YoUrl.additionUrl(YoUrl.parse(url2),new HashMap<String,Object>(){{
            put("age",12);
            put("moment","13");
        }},"tag2");
        System.out.println(url);
    }

}
