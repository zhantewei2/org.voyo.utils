package org.voyo.utils.utils;

public class YoMath {
  public static final String miniSource = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789";
  public static final Long miniSourceLen = 62L;

  public YoMath() {
  }

  public static String randomId(int count) {
    StringBuilder stringBuilder = new StringBuilder();

    while(count-- > 0) {
      stringBuilder.append(randomOne());
    }

    return stringBuilder.toString();
  }

  public static String randomId() {
    return randomId(8);
  }

  public static char randomOne() {
    int index = Double.valueOf(Math.floor(Math.random() * 3.0)).intValue();
    int code;
    if (index == 0) {
      code = randomInt(48, 57);
    } else if (index == 2) {
      code = randomInt(97, 122);
    } else {
      code = randomInt(65, 90);
    }

    return (char)code;
  }

  public static int randomInt(int start, int end) {
    return Double.valueOf(Math.floor(Math.random() * (double)(end - start))).intValue() + start;
  }

  public static String miniLong(Long source) {
    StringBuilder stringBuilder = new StringBuilder();

    do {
      Long y = source % miniSourceLen;
      source = source / miniSourceLen;
      stringBuilder.append("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789".charAt(y.intValue()));
    } while(source >= miniSourceLen);

    return stringBuilder.toString();
  }
}
