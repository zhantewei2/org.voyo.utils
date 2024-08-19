package org.voyo.utils;


import org.voyo.utils.utils.UniqueId;
import org.voyo.utils.utils.YoMap;
import org.voyo.utils.utils.YoMath;
import org.voyo.utils.utils.YoPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

public class App {

  public static <T> void main(String[] args) throws Exception {
    List<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3));
    List<Integer> b=new ArrayList<>(Arrays.asList(3,4,5,6));

    a.removeAll(b);
    System.out.println(a);
  }


}
