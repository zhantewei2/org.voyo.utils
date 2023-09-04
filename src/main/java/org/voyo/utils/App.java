package org.voyo.utils;

import org.voyo.utils.utils.YoMap;
import org.voyo.utils.utils.url.YoUrl;

import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args){
        HashMap<String,Object> a=YoMap.buildMap().put("x","a").put("c","d").get();

    }

}
