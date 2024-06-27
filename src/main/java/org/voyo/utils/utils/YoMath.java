package org.voyo.utils.utils;

public class YoMath {
  /**
   *  exclude a
   */
  public static final String miniSource = "qwertyuiopsdfghjklzxcvbnmQWERTYUIOPSDFGHJKLZXCVBNM0123456789";
  public static final long miniSourceLen =60L;

  public YoMath() {}

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
      long y = source % miniSourceLen;
      source = source / miniSourceLen;
      stringBuilder.append(miniSource.charAt((int)y));
    } while(source != 0);

    return stringBuilder.toString();
  }

  /**
   * minLong 回转
   * @param content
   * @return
   */
  public static Long decodeMiniLong(String content){
    int len=content.length();
    long total=0;
    for(int i=0;i<len;i++){
      int indexValue = miniSource.indexOf(content.charAt(i));
      total += (long)indexValue*(long)Math.pow(miniSourceLen,i);
    }
    return (long)total;
  }
}
